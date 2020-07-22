package com.lieeber.imoocvideo.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MergeVideoMp3 {

	private String ffmpegEXE;
	
	public MergeVideoMp3(String ffmpegEXE) {
		super();
		this.ffmpegEXE = ffmpegEXE;
	}
	
	public void convertor(String videoInputPath, String mp3InputPath, String videoOutputPath) throws Exception {
//		ffmpeg.exe -i 苏州大裤衩.mp4 -i bgm.mp3 -t 7 -y 新的视频.mp4
		List<String> command = new ArrayList<>();
		command.add(ffmpegEXE);
		
		command.add("-i");
		command.add(videoInputPath);
		
		command.add("-i");
		command.add(mp3InputPath);

		command.add("-c:v");
		command.add("copy");
		command.add("-c:a");
		command.add("aac");
		command.add("-strict");
		command.add("experimental");
		command.add("-map");
		command.add("0:v:0");
		command.add("-map");
		command.add("1:a:0");
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
		MergeVideoMp3 ffmpeg = new MergeVideoMp3("C:\\ProgramData\\chocolatey\\bin\\ffmpeg.exe");
		try {
			ffmpeg.convertor("C:\\Users\\lieeb\\Downloads\\imoocvideo_save\\180425CFA4RB6T0H\\userVideo\\wx7d9fa0167fed5edf.o6zAJs0BoCHo1mp3UK_OSUOIX8A8.zwGlmzrbvlq2bccb3a453d600553b2bdb85aa8e7777a.m4v",
					"C:\\Users\\lieeb\\Downloads\\imoocvideo_save\\bgm\\Forward-Declan_DP.mp3",  "C:\\Users\\lieeb\\Downloads\\这是通过java生产的视频.mp4");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
