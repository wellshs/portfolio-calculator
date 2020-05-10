package dev.hyunsuk.portfolio

object config {
  type CrawlingInfo = Map[String, String]

  val crawlerConfig: Map[String, CrawlingInfo] = Map(
    "stooq" -> Map(
      "APPLE" -> "https://stooq.com/q/d/l/?s=aapl.us&d1=19700907&d2=20200317&i=d"
    )
  )

}
