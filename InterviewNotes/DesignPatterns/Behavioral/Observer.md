😎 Observer
        In plain words
            Defines a dependency between objects so that whenever an object changes its state, all its dependents are notified.

        Real world example
            A good example would be the job seekers where they subscribe to some job posting site and they are notified whenever there is a matching job opportunity.

        Wikipedia says
            The observer pattern is a software design pattern in which an object, called the subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods.

        Programmatic example
            Translating our example from above. First of all we have job seekers that need to be notified for a job posting

                class JobPost
                {
                    protected $title;

                    public function __construct(string $title)
                    {
                        $this->title = $title;
                    }

                    public function getTitle()
                    {
                        return $this->title;
                    }
                }

                class JobSeeker implements Observer
                {
                    protected $name;

                    public function __construct(string $name)
                    {
                        $this->name = $name;
                    }

                    public function onJobPosted(JobPost $job)
                    {
                        // Do something with the job posting
                        echo 'Hi ' . $this->name . '! New job posted: '. $job->getTitle();
                    }
                }

            Then we have our job postings to which the job seekers will subscribe

                class EmploymentAgency implements Observable
                {
                    protected $observers = [];

                    protected function notify(JobPost $jobPosting)
                    {
                        foreach ($this->observers as $observer) {
                            $observer->onJobPosted($jobPosting);
                        }
                    }

                    public function attach(Observer $observer)
                    {
                        $this->observers[] = $observer;
                    }

                    public function addJob(JobPost $jobPosting)
                    {
                        $this->notify($jobPosting);
                    }
                }

            Then it can be used as
                // Create subscribers
                $johnDoe = new JobSeeker('John Doe');
                $janeDoe = new JobSeeker('Jane Doe');

                // Create publisher and attach subscribers
                $jobPostings = new EmploymentAgency();
                $jobPostings->attach($johnDoe);
                $jobPostings->attach($janeDoe);

                // Add a new job and see if subscribers get notified
                $jobPostings->addJob(new JobPost('Software Engineer'));

                // Output
                // Hi John Doe! New job posted: Software Engineer
                // Hi Jane Doe! New job posted: Software Engineer





















