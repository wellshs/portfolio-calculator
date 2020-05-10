package dev.hyunsuk.porfolio

import dev.hyunsuk.portfolio.data.crawler.stooqCrawler

object Main extends App{
  val testData = stooqCrawler.fetchData("https://stooq.com/q/d/l/?s=aapl.us&d1=19700907&d2=20200317&i=d")

  println(testData)
}
