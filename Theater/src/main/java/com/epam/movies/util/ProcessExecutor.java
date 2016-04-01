package com.epam.movies.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessExecutor {
	public  static boolean executeProcess(ProcessBuilder processBuilder, String successMsg, String failedMsg)
			throws IOException {
		processBuilder.redirectErrorStream(true);
		Process process = processBuilder.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		while (true) {
			line = reader.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}
		try {
			int processComplete = process.waitFor();
			if (processComplete == 0) {
				System.out.println(successMsg);
				return true;
			} else {
				System.out.println(failedMsg);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;
	}
}
