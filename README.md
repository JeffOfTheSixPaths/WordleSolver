WordleSolver is a fully functioning Wordle game that includes a Solving algorithm to help users if needed. This can be applied to our own wordle or the NYT wordle
You simply input your guesses and our algorithm gives you a word to input based on weights and commonality in words to give you the next best guess
Generally can solve Wordles in 3-4 guesses!

Contributions to this project were made by
    -Jakob B. Olson
    -Gohith N. Reddy
    -Kevin E. Martinez

There are no special things needed to run our program, simply setting up a Javafx configuration where LogInApplication is the main class and the program will launch
Link to the JavaFx SDK - https://gluonhq.com/products/javafx/
All of the logins that can be used are stored in the Users data file
    ~
     test1,1234,3,0,0,0.0
     test2,Abcde1,0,0,0,0.0
     test3,Abcde1,0,0,0,0.0
     asdf,asdf,3,0,0,0.0
     123,C1_loginup,0,0,0,0.0
     test4,Abcde2,0,0,0,0.0
     Jakob,Jakob04,0,0,0,0.0
     Jakob1,Jakob1,0,0,0,0.0
     Gohith,Goober1,0,0,0,0.0
    ~

Or users can create a new login for themselves that will be stored locally.

Issues we ran into are storing and updating the users.csv for recurring streaks and solve counts, as the way we are sourcing does not allow for easy updates to the data resource.
We are working on implementing a fix.
Sometimes the logo image won't show up. I don't know what is causing this, but if you re-run the program, the image will now show.
It's also VERY slow when running the solver on the first guess because it's calculating the values for all 14,000 words


# UML Diagram
---
![UML](https://media.discordapp.net/attachments/1412813532084437014/1447392823656710350/32908d627f6b7f08faac6ee0641f2925.png?ex=6937751c&is=6936239c&hm=5b28ca3059ddf2145af8441699b2a406e4d8e3ee53b85c3fbb8edea17afff2f2&=&format=webp&quality=lossless&width=1741&height=902)

