package com.geekbrains.lavsam.TMDBViewer.model.data

fun getMovies(): List<MovieDetail> {
    return listOf(
        MovieDetail(
            Movie(615457, "Nobody", "", 2021, "84%"),
        "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
           "Drama, Crime, Action, Thriller", "Ilya Naishuller", "1h 32m"
        ),
        MovieDetail(
            Movie(27205, "Inception", "", 2010, "83%"),
            "Cobb, a skilled thief who commits corporate espionage by infiltrating the subconscious of his targets is offered a chance to regain his old life as payment for a task considered to be impossible: \"inception\", the implantation of another person's idea into a target's subconscious.",
            "Action, Science Fiction, Adventure", "Christopher Nolan", "2h 28m"
        )
    )
}