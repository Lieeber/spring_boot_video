package com.lieeber.imoocvideo.utils;

import com.lieeber.imoocvideo.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MergeVideoMp3 {

    private String ffmpegEXE;

    public MergeVideoMp3(String ffmpegEXE) {
        super();
        this.ffmpegEXE = ffmpegEXE;
    }

    public void convertor(String videoInputPath, String mp3InputPath, String videoOutputPath, float duration) {
        try {
            String tempVideoPath = Constants.INSTANCE.getRootPath()+"/temp/"+ UUID.randomUUID()+".mp4";
            {
                List<String> command = new ArrayList<>();
                command.add(ffmpegEXE);

                command.add("-i");
                command.add(videoInputPath);
                command.add("-vcodec");
                command.add("copy");
                command.add("-an");
                command.add(tempVideoPath);

                for (String c : command) {
                    System.out.print(c + " ");
                }
                System.out.println("============");

                ProcessBuilder builder = new ProcessBuilder(command);
                Process process = builder.start();
            }
//		InputStream errorStream = process.getErrorStream();
//		InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
//		BufferedReader br = new BufferedReader(inputStreamReader);
//		br.close();
//		inputStreamReader.close();
//		errorStream.close();

            {
                Thread.sleep(100);
                List<String> command = new ArrayList<>();
                command.add(ffmpegEXE);
                command.add("-i");
                command.add(tempVideoPath);
                command.add("-i");
                command.add(mp3InputPath);
                command.add("-t");
                command.add(String.valueOf(duration));
                command.add("-y");
                command.add(videoOutputPath);
                for (String c : command) {
                    System.out.print(c + " ");
                }
                ProcessBuilder builder = new ProcessBuilder(command);
                Process process = builder.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        MergeVideoMp3 ffmpeg = new MergeVideoMp3(Constants.INSTANCE.getFfmpegPath());
//        try {
        ffmpeg.convertor("/Users/lieeber/Downloads/wx_camera_1559957341281.mp4",
                "/Users/lieeber/Downloads/Funky_Sting.mp3",
                "/Users/lieeber/Downloads/合并之后的视频.mp4", 10);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
