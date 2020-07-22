package com.lieeber.imoocvideo.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FFMpegTest {

	private String ffmpegEXE;
	
	public FFMpegTest(String ffmpegEXE) {
		super();
		this.ffmpegEXE = ffmpegEXE;
	}
	
	public void convertor(String videoInputPath, String videoOutputPath) throws Exception {
//		ffmpeg -i input.mp4 -y output.avi
		List<String> command = new ArrayList<>();
		command.add(ffmpegEXE);
		
		command.add("-i");
		command.add(videoInputPath);
		command.add("-y");
		command.add(videoOutputPath);
		
		for (String c : command) {
			System.out.print(c + " ");
		}
		
		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.start();
		
		InputStream errorStream = process.getErrorStream();
		InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
		BufferedReader br = new BufferedReader(inputStreamReader);
		
		String line = "";
		while ( (line = br.readLine()) != null ) {

		}

		br.close();
		inputStreamReader.close();
		errorStream.close();

	}

	public static void main(String[] args) {
		FFMpegTest ffmpeg = new FFMpegTest("C:\\ProgramData\\chocolatey\\bin\\ffmpeg.exe");
		try {
			ffmpeg.convertor("C:\\Users\\lieeb\\Downloads\\imoocvideo_save\\180425CFA4RB6T0H\\userVideo\\wx7d9fa0167fed5edf.o6zAJs0BoCHo1mp3UK_OSUOIX8A8.zwGlmzrbvlq2bccb3a453d600553b2bdb85aa8e7777a.m4v",
					"C:\\Users\\lieeb\\Downloads\\ffff.avi");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
