package applications.service;

import controllers.VideoController;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VideoService {
    private final Scanner scanner = new Scanner(System.in);

    private final VideoController videoCotroller;

    public VideoService(VideoController videoCotroller) {
        this.videoCotroller = videoCotroller;
    }



    public void startVideoServiceForStudent() {
        while (true) {
            try {
                System.out.println("Here you can watch the video of course");
                System.out.println("Please enter the course ID to see the video");
                Long courseId = scanner.nextLong();
                System.out.println(videoCotroller.getAllCourseVideos(courseId));

                System.out.println("Please enter Video id which you want to see ");
                Long videoId = scanner.nextLong();
                scanner.nextLine();
                String videoPath = videoCotroller.getVideo(videoId);

                File file = new File(videoPath);
                if (!file.exists()) {
                    System.out.println("File not found!");
                    return;
                }

                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                System.out.println("Do you want to Exit? (yes)");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("yes")) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }




    private void menuVideoForTeacher() {
        System.out.println();
        System.out.println("Here you can manipulate your course's video");
        System.out.println("Select one of the following options:");
        System.out.println("1. Add a new video for a course");
        System.out.println("2. Delete a video from a course");
        System.out.println("3. See all videos in a course");
        System.out.println("0. Exit");
    }

    public void startVideoServiceForTeacher() {
        while (true) {
            try {
                menuVideoForTeacher();
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        createVideo();
                        break;
                    case 2:
                        deleteVideo();
                        break;
                    case 3:
                        getAllVideos();
                        break;
                    default:
                        return;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid option!");
            } catch (Exception error) {
                System.out.println(error.getMessage());
            }
        }
    }

    private void createVideo() {
        System.out.println();
        System.out.println("Here you can create a new video for a course");
        System.out.println("Enter the course ID which this video will be belong to");
        Long courseId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter title of the video");
        String title = scanner.nextLine();
        System.out.println("Enter file path of the video");
        String file = scanner.nextLine();
        System.out.println(videoCotroller.createNewVideo(courseId, title, file));
    }
    private void deleteVideo() {
        System.out.println();
        System.out.println("Here you can remove a video from a course");
        System.out.println("Enter the video ID which will be deleted");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println(videoCotroller.deleteVideo(id));
    }
    private void getAllVideos() {
        System.out.println();
        System.out.println("Here you can get all videos which belongs to a course");
        System.out.println("Enter the course ID");
        Long courseId = scanner.nextLong();
        scanner.nextLine();
        System.out.println(videoCotroller.getAllCourseVideos(courseId));
    }
}
