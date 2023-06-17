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
 * 
 * 		(+)Extra functionality
 * 		(—)Unimplemented functionality
 * 		(~)Different implementation, same result.
 * 
 * 1)(+) The secretary can add more than one professor name while inserting courses(separated with commas).
 * 
 * 2)(—) In this version, there isn't any functionality specifically for exporting the final schedule to publish to students with ease.
 * 
 * 3)(+) Not all rooms have the same capacity. Amphitheaters and auditoriums have different capacities 
 *    (values which the secretary inserts to the program).
 *
 * 4)(+) Since the number of enrolled students depends on secretary's input, it isn't certain that all students can be accommodated
 * in one room. Professors select rooms until no students remain.
 * 
 * 5)(+) The secretary is also required to select exam period and start/end date (although not used in the current version).
 * 
 * 6)(+) All rooms are available when selecting an available time since two courses can't be scheduled simultaneously.
 * 
 * 7)(~)Select course and schedule exam are two different GUI classes. The professor first selects course, presses Confirm, then 
 *    the schedule exam GUI appears, professor selects date, searches for available hours in that day and selects any rooms until 
 *    no students remain. The sequence of actions is different in SRS UC02.
 * 
 * 8)(—)There is no cancel option in the InsertCourseGUI as stated in the SRS UC03 4.a.1.
 * 
 * 9)(+) UC05 — Insert number of rooms: capacities are also required.
 * 
 * 10)(+) UC06 — Insert Course: also requires number of enrolled students
 * 
 * 11)(+) UC07 — The schedule can be viewed through the SecretaryGUI2 window [View Schedule] button and not through the RoomsGUI.
 */
public class Main {

	public static void main(String[] args) {

		LoginGUI.getInstance();	

	}

}
