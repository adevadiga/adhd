Simple Factory:
--------------
    In plain words
        Simple factory simply generates an instance for client without exposing any instantiation logic to the client.

    Real world example
        Consider, you are building a house and you need doors. You can either put on your carpenter clothes, bring some wood, glue, nails and all the tools required to build the door and start building it in your house or you can simply call the factory and get the built door delivered to you so that you don't need to learn anything about the door making or to deal with the mess that comes with making it.

    Programmatic Example
        First of all we have a door interface and the implementation

            public interface Door {
                public int getHeight();
                public int getWidth();
            }

            public class WoodenDoor implements Door {
                ...
            }

        Then we have our door factory that makes the door and returns it
            
            public class DoorFactory {
                public static Door makeDoor(int height, int width) {
                    return new WoodenDoor(height, width);
                }
            }

        Usage
            Door frontDoor = DoorFactory.makeDoor(10,10);
            Door backDoor = DoorFactory.makeDoor(100,10);


        When to Use?
            When creating an object is not just a few assignments and involves some logic, it makes sense to put it in a dedicated factory instead of repeating the same code everywhere.




Factory Method:
-------------
    In plain words
        It provides a way to delegate the instantiation logic to child classes.

    Real world example
        Consider the case of a hiring manager. It is impossible for one person to interview for each of the positions. Based on the job opening, she has to decide and delegate the interview steps to different people.

    Programmatic Example
        Taking our hiring manager example above. First of all we have an interviewer interface and some implementations for it

            public interface Interviewer {
                public void askQuestions();
            }

            public class Developer implements Interviewer {
                public void askQuestions(){
                    echo 'Asking about design patterns!';
                }
            }

            public class CommunityExecutive implements Interviewer {
                public void askQuestions(){
                    echo 'Asking about community building';
                }
            }

        Now let us create our HiringManager

            public abstract class HiringManager {

                // Factory method
                abstract protected Interviewer makeInterviewer();

                public void takeInterview(){
                    Interviewer interviewer = this.makeInterviewer();
                    interviewer.askQuestions();
                }
            }

        Now any child can extend it and provide the required interviewer

            class DevelopmentManager extends HiringManager {

                protected Interviewer makeInterviewer() {
                    return new Developer();
                }
            }

            class MarketingManager extends HiringManager{

                protected Interviewer makeInterviewer() {
                    return new CommunityExecutive();
                }
            }

        and then it can be used as

            Interviewer devManager = new DevelopmentManager();
            devManager.takeInterview(); // Output: Asking about design patterns

            Interviewer marketingManager = new MarketingManager();
            marketingManager.takeInterview(); // Output: Asking about community building.


    When to use?
        Useful when there is some generic processing in a class but the required sub-class is dynamically decided at runtime. Or putting it in other words, when the client doesn't know what exact sub-class it might need.


=================

    Abstract Factory:
        In plain words
            A factory of factories; a factory that groups the individual but related/dependent factories together without specifying their concrete classes.

        Real world example
            Extending our door example from Simple Factory. Based on your needs you might get a wooden door from a wooden door shop, iron door from an iron shop or a PVC door from the relevant shop. Plus you might need a guy with different kind of specialties to fit the door, for example a carpenter for wooden door, welder for iron door etc. As you can see there is a dependency between the doors now, wooden door needs carpenter, iron door needs a welder etc.

        Programmatic Example
            Translating the door example above. First of all we have our Door interface and some implementation for it

                interface Door
                {
                    public function getDescription();
                }

                class WoodenDoor implements Door
                {
                    public function getDescription()
                    {
                        echo 'I am a wooden door';
                    }
                }

                class IronDoor implements Door
                {
                    public function getDescription()
                    {
                        echo 'I am an iron door';
                    }
                }

            Then we have some fitting experts for each door type
                interface DoorFittingExpert
                {
                    public function getDescription();
                }

                class Welder implements DoorFittingExpert
                {
                    public function getDescription()
                    {
                        echo 'I can only fit iron doors';
                    }
                }

                class Carpenter implements DoorFittingExpert
                {
                    public function getDescription()
                    {
                        echo 'I can only fit wooden doors';
                    }
                }

            Now we have our abstract factory that would let us make family of related objects i.e. wooden door factory would create a wooden door and wooden door fitting expert and iron door factory would create an iron door and iron door fitting expert

                interface DoorFactory
                {
                    public function makeDoor(): Door;
                    public function makeFittingExpert(): DoorFittingExpert;
                }

                // Wooden factory to return carpenter and wooden door
                class WoodenDoorFactory implements DoorFactory
                {
                    public function makeDoor(): Door
                    {
                        return new WoodenDoor();
                    }

                    public function makeFittingExpert(): DoorFittingExpert
                    {
                        return new Carpenter();
                    }
                }

                // Iron door factory to get iron door and the relevant fitting expert
                class IronDoorFactory implements DoorFactory
                {
                    public function makeDoor(): Door
                    {
                        return new IronDoor();
                    }

                    public function makeFittingExpert(): DoorFittingExpert
                    {
                        return new Welder();
                    }
                }

            And then it can be used as

                $woodenFactory = new WoodenDoorFactory();

                $door = $woodenFactory->makeDoor();
                $expert = $woodenFactory->makeFittingExpert();

                $door->getDescription();  // Output: I am a wooden door
                $expert->getDescription(); // Output: I can only fit wooden doors

                // Same for Iron Factory
                $ironFactory = new IronDoorFactory();

                $door = $ironFactory->makeDoor();
                $expert = $ironFactory->makeFittingExpert();

                $door->getDescription();  // Output: I am an iron door
                $expert->getDescription(); // Output: I can only fit iron doors

            As you can see the wooden door factory has encapsulated the carpenter and the wooden door,
             also iron door factory has encapsulated the iron door and welder. And thus it had helped us make sure that for each of the created door, we do not get a wrong fitting expert.


        When to use?
            When there are interrelated dependencies with not-that-simple creation logic involved

































