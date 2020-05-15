package com.cognizantTechno.test.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandLineExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineExecutor.class);
    private static String command;
    private static String output;
    private static Integer exitCode;
    private static Integer commandTimeouts = 0;

    public CommandLineExecutor() {
    }

    public static String execute(String command, Integer timeoutInSec) {
        command = command;
        output = null;
        Process process = null;
        String errorMsg = "Command not executed successfully.";

        try {
            process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", command});
            process.waitFor(20L, TimeUnit.SECONDS);
            exitCode = process.exitValue();
            output = (String)((Stream)Stream.of(process.getErrorStream(), process.getInputStream()).parallel()).map((isForOutput) -> {
                StringBuilder output = new StringBuilder();

                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader((InputStream) isForOutput));
                    Throwable var4 = null;

                    try {
                        String line;
                        while((line = br.readLine()) != null) {
                            output.append(line);
                            output.append("\n");
                        }

                        br.close();
                    } catch (Throwable var14) {
                        var4 = var14;
                        throw var14;
                    } finally {
                        if (br != null) {
                            if (var4 != null) {
                                try {
                                    br.close();
                                } catch (Throwable var13) {
                                    var4.addSuppressed(var13);
                                }
                            } else {
                                br.close();
                            }
                        }

                    }
                } catch (IOException var16) {
                    LOGGER.info(errorMsg + ":" + output);
                }

                return output;
            }).collect(Collectors.joining());
        } catch (Exception var8) {
            if (var8 instanceof InterruptedException) {
                commandTimeouts = commandTimeouts + 1;
            }

            LOGGER.info(errorMsg + ":" + output);
        } finally {
            process.destroyForcibly();
        }

        return output;
    }

    public static String execute(String command) {
        command = command;
        output = null;
        final String errorMsg = "Command not executed successfully.";

        try {
            final Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", command});
            (new Thread(new Runnable() {
                public void run() {
                    CommandLineExecutor.output = (String)((Stream)Stream.of(process.getErrorStream(), process.getInputStream()).parallel()).map((isForOutput) -> {
                        StringBuilder output = new StringBuilder();

                        try {
                            BufferedReader br = new BufferedReader(new InputStreamReader((InputStream) isForOutput));
                            Throwable var4 = null;

                            try {
                                String line = null;

                                while((line = br.readLine()) != null) {
                                    output.append(line);
                                    output.append("\n");
                                    System.out.println(line);
                                }

                                br.close();
                            } catch (Throwable var14) {
                                var4 = var14;
                                throw var14;
                            } finally {
                                if (br != null) {
                                    if (var4 != null) {
                                        try {
                                            br.close();
                                        } catch (Throwable var13) {
                                            var4.addSuppressed(var13);
                                        }
                                    } else {
                                        br.close();
                                    }
                                }

                            }
                        } catch (IOException var16) {
                            CommandLineExecutor.LOGGER.info(errorMsg + ":" + output);
                        }

                        return output;
                    }).collect(Collectors.joining());
                }
            })).start();
            process.waitFor();
        } catch (Exception var3) {
            if (var3 instanceof InterruptedException) {
                commandTimeouts = commandTimeouts + 1;
            }

            LOGGER.info(errorMsg + ":" + output);
        }

        return output;
    }
}
