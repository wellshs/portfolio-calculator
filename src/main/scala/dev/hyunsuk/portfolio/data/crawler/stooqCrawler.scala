package dev.hyunsuk.portfolio.data.crawler

import scala.io.Source
import scala.util.Try

import com.github.tototoshi.csv._

object stooqCrawler extends AbstractCrawler {
  def createURLByCode(code: String): String = {
    s"https://stooq.com/q/d/l/?s=$code&i=d"
  }

  def getDataByCode(code: String): String = {
    fetchData(createURLByCode(code))
  }

  override def processData(dataSource: Source): String = {
    val reader = CSVReader.open(dataSource)

    val StooqData = reader.all().tail.map(
      (rawData) => StooqDatum(
        rawData(0),
        rawData(1).toDouble,
        rawData(2).toDouble,
        rawData(3).toDouble,
        rawData(4).toDouble,
        Try(rawData(5).toLong).getOrElse(0)
      )
    )

    StooqData.map((datum) => (datum.Date, datum.OpenValue)).toString()
  }
}

case class StooqDatum(Date: String, OpenValue: Double, HighValue: Double, LowValue: Double, CloseValue: Double, Volume: Long)