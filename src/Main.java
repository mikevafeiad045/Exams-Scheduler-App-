/**
 * ~~~~~~~~~~~~~~~Exam Scheduling App~~~~~~~~~~~~~~~~~~
 * 
 * Application for scheduling university semester exams. 
 * Intended for use by the secretary and professors.
 *
 * @version 17 Jun 2023
 * @author Michalis Vafeiadis, Dimitra Papadopoulou, Eleni Loula, Stavros Samaras, Charina Makri
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * 
 * Differences with Software Requirements Specifications Document (SRS):
 *	(—)Unimplemented functionality
 *	(~)Different implementation, same result.
 *
 * 1)(—) In this version, there isn't any functionality specifically for exporting the final schedule to publish to students with ease.
 * 2)(~)Select course and schedule exam are two different GUI classes. The professor first selects course, presses Confirm, then 
 *    the schedule exam GUI appears, professor selects date, searches for available hours in that day and selects any rooms until 
 *    no students remain. The sequence of actions is different in SRS UC02.
 * 
 * 3)(—)There is no cancel option in the InsertCourseGUI as stated in the SRS UC03 4.a.1.
 */
public class Main {

	public static void main(String[] args) {

		LoginGUI.getInstance();	

	}

}
