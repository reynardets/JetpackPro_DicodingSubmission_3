package com.example.movieapp.data.utils

import com.example.movieapp.ResultsMovieItem
import com.example.movieapp.ResultsTvItem
import com.example.movieapp.data.source.local.entity.MovieEntity
import com.example.movieapp.data.source.local.entity.TvEntity

object DataDummy {
    fun generateDummyMovie(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                399579,
                "Alita: Battle Angle",
                "/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                "2019-01-31",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                7.2
            )
        )

        movies.add(
            MovieEntity(
                297802,
                "Aquaman",
                "/xLPffWMhMj1l50ND3KchMjYoKmE.jpg",
                "2018-12-07",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orms half-human, half-Atlantean brother and true heir to the throne.",
                6.9
            )
        )

        movies.add(
            MovieEntity(
                424694,
                "Bohemian Rhapsody",
                "/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
                "2018-10-24",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock and roll band Queen in 1970. Hit songs become instant classics. When Mercurys increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                8.0
            )
        )

        return movies
    }

    fun generateDummyTv(): List<TvEntity> {
        val tv = ArrayList<TvEntity>()

        tv.add(
            TvEntity(
                12609,
                "Dragon Ball",
                "1986-02-26",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Gokus home. Together, they set off to find all seven and to grant her wish.",
                "/tZ0jXOeYBWPZ0OWzUhTlYvMF7YR.jpg",
                8.1
            )
        )

        tv.add(
            TvEntity(
                46261,
                "Fairy Tail",
                "2009-10-12",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isnt just any ordinary kid, hes a member of one of the worlds most infamous mage guilds: Fairy Tail.",
                "/jsYTctFnK8ewomnUgcwhmsTkOum.jpg",
                7.8
            )
        )

        tv.add(
            TvEntity(
                60735,
                "The Flash",
                "2014-10-07",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only meta-human who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it wont be long before the world learns what Barry Allen has become...The Flash.",
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7
            )
        )

        return tv
    }

    fun generateMovieResponse(): List<ResultsMovieItem> {
        val movies = ArrayList<ResultsMovieItem>()

        movies.add(
            ResultsMovieItem(
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Alita: Battle Angle",
                "/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                "2019-01-31",
                7.2,
                399579
            )
        )

        movies.add(
            ResultsMovieItem(
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orms half-human, half-Atlantean brother and true heir to the throne.",
                "Aquaman",
                "/xLPffWMhMj1l50ND3KchMjYoKmE.jpg",
                "2018-12-07",
                6.9,
                297802
            )
        )

        movies.add(
            ResultsMovieItem(
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock and roll band Queen in 1970. Hit songs become instant classics. When Mercurys increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "Bohemian Rhapsody",
                "/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
                "2018-10-24",
                8.0,
                424694
            )
        )

        return movies
    }

    fun generateTvResponse(): List<ResultsTvItem> {
        val tv = ArrayList<ResultsTvItem>()

        tv.add(
            ResultsTvItem(
                "1986-02-26",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Gokus home. Together, they set off to find all seven and to grant her wish.",
                "/tZ0jXOeYBWPZ0OWzUhTlYvMF7YR.jpg",
                8.1,
                "Dragon Ball",
                12609
            )
        )

        tv.add(
            ResultsTvItem(
                "2009-10-12",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isnt just any ordinary kid, hes a member of one of the worlds most infamous mage guilds: Fairy Tail.",
                "/jsYTctFnK8ewomnUgcwhmsTkOum.jpg",
                7.8,
                "Fairy Tail",
                46261
            )
        )

        tv.add(
            ResultsTvItem(
                "2014-10-07",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only meta-human who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it wont be long before the world learns what Barry Allen has become...The Flash.",
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7,
                "The Flash",
                60735
            )
        )

        return tv
    }

    fun generateDummyDetailMovie(id: Int): MovieEntity? {
        for (movie in generateDummyMovie()) {
            if (id == movie.id)
                return movie
        }
        return null
    }

    fun generateDummyDetailTv(id: Int): TvEntity? {
        for (tv in generateDummyTv()) {
            if (id == tv.id)
                return tv
        }
        return null
    }

    fun generateMovieDetailResponse(): ResultsMovieItem {

        return ResultsMovieItem(
            "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            "Alita: Battle Angle",
            "/xRWht48C2V8XNfzvPehyClOvDni.jpg",
            "2019-01-31",
            7.2,
            122
        )
    }

    fun generateTvDetailResponse(): ResultsTvItem {

        return ResultsTvItem(
            "1986-02-26",
            "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Gokus home. Together, they set off to find all seven and to grant her wish.",
            "/tZ0jXOeYBWPZ0OWzUhTlYvMF7YR.jpg",
            8.1,
            "Dragon Ball",
            12609
            //arrayOf(25).toList()
        )
    }

    fun generateDummyFavMovie(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                399579,
                "Alita: Battle Angle",
                "/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                "2019-01-31",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                7.2
            )
        )

        movies.add(
            MovieEntity(
                297802,
                "Aquaman",
                "/xLPffWMhMj1l50ND3KchMjYoKmE.jpg",
                "2018-12-07",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orms half-human, half-Atlantean brother and true heir to the throne.",
                6.9
            )
        )

        return movies
    }

    fun generateDummyFavTv(): List<TvEntity> {
        val tv = ArrayList<TvEntity>()

        tv.add(
            TvEntity(
                12609,
                "Dragon Ball",
                "1986-02-26",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Gokus home. Together, they set off to find all seven and to grant her wish.",
                "/tZ0jXOeYBWPZ0OWzUhTlYvMF7YR.jpg",
                8.1
            )
        )

        tv.add(
            TvEntity(
                46261,
                "Fairy Tail",
                "2009-10-12",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isnt just any ordinary kid, hes a member of one of the worlds most infamous mage guilds: Fairy Tail.",
                "/jsYTctFnK8ewomnUgcwhmsTkOum.jpg",
                7.8
            )
        )

        return tv
    }

    fun generateDummyDetailFavMovie(id: Int): MovieEntity? {
        for (movie in generateDummyFavMovie()) {
            if (id == movie.id)
                return movie
        }
        return null
    }

    fun generateDummyDetailFavTv(id: Int): TvEntity? {
        for (tv in generateDummyFavTv()) {
            if (id == tv.id)
                return tv
        }
        return null
    }


}