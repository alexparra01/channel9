package com.test.channel9.util

import com.test.channel9.domain.models.Asset
import com.test.channel9.domain.models.NewsArticles
import com.test.channel9.domain.models.RelatedImage

object Samples {

    val newsArticles = NewsArticles(
        listOf(
            Asset(
                url = "http://www.afr.com/news/policy/climate/duelling-billionaires-burn-sun-cable-20230111-p5cbw2",
                headline = "The spectacular split between Sun Cable’s duelling billionaires",
                theAbstract = "It is feasible the world’s most ambitious solar energy generation project will go ahead. But don’t expect billionaires Mike Cannon-Brookes and Andrew Forrest to both be involved.",
                byLine = "Tony Boyd",
                relatedImages = listOf(
                    RelatedImage(
                        type = "afrWoodcutAuthorImage",
                        url = "https://www.fairfaxstatic.com.au/content/dam/images/h/1/c/l/k/u/image.imgtype.afrWoodcutAuthorImage.140x140.png/1553052379451.png"
                    ),
                    RelatedImage(
                        type = "wideLandscape",
                        url = "https://www.fairfaxstatic.com.au/content/dam/images/h/2/9/3/x/w/image.related.wideLandscape.1500x844.p5cbw2.13zzqx.png/1673486560933.jpg"
                    ),
                    RelatedImage(
                        type = "thumbnail",
                        url = "https://www.fairfaxstatic.com.au/content/dam/images/h/2/9/3/x/w/image.related.thumbnail.375x250.p5cbw2.13zzqx.png/1673486560933.jpg"
                    )
                ),
                timeStamp = 1672967184411
            ),
            Asset(
                url = "http://www.afr.com/news/policy/climate/double-bay-jesus-versus-god-20230111-p5cbwt",
                headline = "The spectacular split between Sun Cable’s duelling billionaires",
                theAbstract = "Mike Cannon-Brookes and Andrew Forrest’s dispute over Sun Cable is a fascinating fight between two giant egos.",
                byLine = "Aaron Patrick",
                relatedImages = listOf(
                    RelatedImage(
                        type = "afrWoodcutAuthorImage",
                        url = "https://www.fairfaxstatic.com.au/content/dam/images/h/1/c/l/k/u/image.imgtype.afrWoodcutAuthorImage.140x140.png/1553052379451.png"
                    ),
                    RelatedImage(
                        type = "wideLandscape",
                        url = "https://www.fairfaxstatic.com.au/content/dam/images/h/2/9/3/x/w/image.related.wideLandscape.1500x844.p5cbw2.13zzqx.png/1673486560933.jpg"
                    ),
                    RelatedImage(
                        type = "thumbnail",
                        url = "https://www.fairfaxstatic.com.au/content/dam/images/h/2/9/3/x/w/image.related.thumbnail.375x250.p5cbw2.13zzqx.png/1673486560933.jpg"
                    )
                ),
                timeStamp = 1673427450000
            )
        )
    )

}