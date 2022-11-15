#  management of volunteers Organization Project 
## **~the organization structure** 
 I created a system for managing volunteers so that I built it according to a hierarchical structure where the member will be an organization which is an abstract class, and two interfaces one for the ability to volunteer and the other for providing the ability to manage, and now we will create the types of members so first we will create the volunteer who inherits from an organization member and receives the ability to volunteer and is a bequeather Directly to a field person is called an "inspector" who is indeed a volunteer and therefore inherits directly from a volunteer, however he now receives an addition of management from the Interpres which is the ability to manage and at the end we will build the manager who is a little different in that he is not a volunteer but a paid employee and therefore he inherits from a member of an organization but receives managerial ability.


![This is an image](https://github.com/moshe-h-m/PTM1OrgProject/blob/master/organization%20member.png)

## **~the principle of the functionality** 
 When I built the functionality of the system, I established an arrangement so that the "admin" receives the data from the field man "inspector" who in turn receives the data from all the volunteers so that they only know the field man,
 For this purpose, we used a "thread pool" so that the volunteers of each field person could run unimpeded and would not conflict with the possibility of volunteering when it is reported to another field person.
