# To-Do-App

This is an android application. 
It will allow user to create a task-list, set the date and priority of the task.

**Framework used:** Android studio 
**Languages used:** Xml, Java




## Problem Statement
Get  familiar  with  Android  intents  (Explicit/Implicit),  data passing between activities, starting activity for result, and alert dialogs. This is a To Do List app. 
![image](https://user-images.githubusercontent.com/70915043/137661161-37d6d1e5-aa49-48c0-8758-355ae46d8ff2.png)
![image](https://user-images.githubusercontent.com/70915043/137661180-0480b27e-efed-49a5-a788-2812608b31d4.png)


**Part 1 (50 Points):** Main Activity The Main Activity shows the number of tasks, the most recent task as shown in Fig 1(a). The activity requirements are as follows: 

1.Create  a  Task  class.  The  tasks  list  should  be  stored  in  an ArrayList  of  Tasks  in  the Main Activity. 
2.The  number  of  tasks  currently  in  the  list  should  be  displayed  in  at  the  top  of  the activity as shown in Fig 1(a).
3.The  most  recent  task,  which  is  the  task  with  the  lowest  date  should  be  displayed  at the top of the activity as shown in Fig 1(a). If the list is empty show the upcoming task as “None”. 
4.Clicking on the “VIEW TASK” button should display an AlertDialog showing the tasks list names as shown in Fig 1(b).  a.Check the example posted in https://developer.android.com/guide/topics/ui/dialogsin order to implement an AlertDialog with a list of choices. b.Upon selecting a task from the list, the app should start the Display Task Activity  for result, as shown in Fig 1(c) to display the details of the selected task. c.Clicking cancel should dismiss the displayed AlertDialog.
5.Clicking  on  the  “CREATE  A  TASK”  button  should  display  the  Create  Task  Activity shown in Fig 2(a).  
a.The Create Task Activity should be started for result as it should return the created Task object to be stored in the Main activity list of tasks. b.Upon  returning  from  the  “Create  Task Activity”  the  newly  created  task  should  be stored in the task list that is stored in the Main Activity. In addition, the number of tasks  and  upcoming  task  displayed  should  be  updated  to  account  for  the  newly added task. 

**Part 3 (25 Points):** Display Task Activity The Display Task activity is shown in Figure 1(c). The requirements are as follows:
1.The  Display  Task  activity  should  receive  the  task  to  be  displayed  from  the  Main Activity. The task information should be displayed as shown in Fig 1(c). 2.Clicking  the  “CANCEL”  button  should  finish  the  Display  Task  activity,  which  should show the Main activity. 
3.Clicking  the  “DELETE”  button  should  return  the  task  object  back  as  a  result  to  the Main Activity in order to be deleted from the list maintained in the Main activity. Then finish the Display Task activity. 

**Part 2 (25 Points):** Create Task Activity The Create Task Activity allows the user to create a new task as shown in Fig 2(a). The requirements are as follows: 
1.Each task should have a name, date, and a priority as shown in Fig 2(a). 
2.Clicking the “Set date” button should show a Date Picker Dialog as shown in Figure 2(a).  Upon  selecting  a  date  in  the  Date  Picker  and  clicking  “OK”  then  the  selected date should be displayed in the Create Task Activity as shown in Fig 2(c). Please use https://developer.android.com/reference/android/app/DatePickerDialog for information related to the date picker dialog.
3.Clicking the “SUBMIT” button: a.Check  that  all  the  task  entries  are  entered  correctly.  If  not  display  a  Toast message.  If  all  the  entries  are  entered  correctly,  then  create  a  Task  object  and send it back as a result to the Main Activity, then finish the Create Task Activity. 
4.Clicking the “CANCEL” button then finish the Create Task Activity, which should show the Main activity. 

## Snapshots of App

![To-Do list](https://user-images.githubusercontent.com/70915043/137396800-6020b714-6fdc-4a1b-b0b3-667bc8aab18f.PNG)


![Create Task](https://user-images.githubusercontent.com/70915043/137396969-59993bc2-bb92-4dd7-b1cb-e2141a27c8de.PNG)


![ViewTask](https://user-images.githubusercontent.com/70915043/137396734-18857cca-ff64-4556-acdb-077386fbb6ec.PNG)

