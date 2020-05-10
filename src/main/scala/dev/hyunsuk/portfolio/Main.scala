package dev.hyunsuk.portfolio

import dev.hyunsuk.portfolio.data.crawler.stooqCrawler

import dev.hyunsuk.portfolio.config.crawlerConfig


object Main extends App {

  // Initialize Crawling
  crawlerConfig.foreach(
    config => config._1 match{
      case "stooq" => stooqCrawler.fetchMultipleData(config._2.values)
    }
  )
}
