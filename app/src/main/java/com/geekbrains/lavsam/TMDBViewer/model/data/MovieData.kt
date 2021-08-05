package com.geekbrains.lavsam.TMDBViewer.model.data

fun getMovies(): List<MovieDetail> {
    return listOf(
        MovieDetail(
            Movie(615457, "Nobody", "", 2021, "84%"),
        "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
           "Drama, Crime, Action, Thriller", "Ilya Naishuller", "1h 32m"
        ),
        MovieDetail(
            Movie(680, "Pulp Fiction", "", 1994, "85%"),
            "A burger-loving hit man, his philosophical partner, a drug-addled gangster's moll and a washed-up boxer converge in this sprawling, comedic crime caper. Their adventures unfurl in three stories that ingeniously trip back and forth in time.",
            "Thriller, Crime", "Quentin Tarantino", "2h 34m"
        ),
        MovieDetail(
            Movie(9659, "Mad Max", "", 1979, "67%"),
            "In a dystopian world, a vengeful Australian policeman named Max sets out to stop a violent motorcycle gang.",
            "Adventure, Action, Thriller, Science Fiction", "George Miller", "1h 31m"
        ),
        MovieDetail(
            Movie(27205, "Inception", "", 2010, "83%"),
            "Cobb, a skilled thief who commits corporate espionage by infiltrating the subconscious of his targets is offered a chance to regain his old life as payment for a task considered to be impossible: \"inception\", the implantation of another person's idea into a target's subconscious.",
            "Action, Science Fiction, Adventure", "Christopher Nolan", "2h 28m"
        )
    )
}