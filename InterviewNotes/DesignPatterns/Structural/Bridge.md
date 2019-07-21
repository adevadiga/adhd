Bridge
    In Plain Words 
        Bridge pattern is about preferring composition over inheritance. Implementation details are pushed from a hierarchy to another object with a separate hierarchy.

    Real world example
        Consider you have a website with different pages and you are supposed to allow the user to change the theme. What would you do? Create multiple copies of each of the pages for each of the themes or would you just create separate theme and load them based on the user's preferences? Bridge pattern allows you to do the second i.e.

    Programmatic Example
        interface WebPage
        {
            public function __construct(Theme $theme);
            public function getContent();
        }

        class About implements WebPage
        {
            protected $theme;

            public function __construct(Theme $theme)
            {
                $this->theme = $theme;
            }

            public function getContent()
            {
                return "About page in " . $this->theme->getColor();
            }
        }

        class Careers implements WebPage
        {
            protected $theme;

            public function __construct(Theme $theme)
            {
                $this->theme = $theme;
            }

            public function getContent()
            {
                return "Careers page in " . $this->theme->getColor();
            }
        }

    And the separate theme hierarchy
        interface Theme
        {
            public function getColor();
        }

        class DarkTheme implements Theme
        {
            public function getColor()
            {
                return 'Dark Black';
            }
        }
        class LightTheme implements Theme
        {
            public function getColor()
            {
                return 'Off white';
            }
        }
        class AquaTheme implements Theme
        {
            public function getColor()
            {
                return 'Light blue';
            }
        }

    And both the hierarchies
        $darkTheme = new DarkTheme();

        $about = new About($darkTheme);
        $careers = new Careers($darkTheme);

        echo $about->getContent(); // "About page in Dark Black";
        echo $careers->getContent(); // "Careers page in Dark Black";









