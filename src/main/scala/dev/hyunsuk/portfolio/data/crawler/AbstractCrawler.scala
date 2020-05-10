package dev.hyunsuk.portfolio.data.crawler

import java.io.{BufferedWriter, File, FileWriter}
import java.nio.file.{Files, Paths}

import scala.io.Source

abstract class AbstractCrawler {
  val dataFolder = "data/"

  private def md5HashString(s: String): String = {
    import java.math.BigInteger
    import java.security.MessageDigest
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(s.getBytes)
    val bigInt = new BigInteger(1, digest)
    val hashedString = bigInt.toString(16)
    hashedString
  }


  //  crawler가 가져야 하는 기본적인 속성을 정의합니다.
  //  여러 사이트에 대해서 crawler를 정의할 수 있으므로, 공동퇸 속성을 만들어 둡니다.
  def fetchData(url: String): String = {
    val cachedFilePath = dataFolder + md5HashString(url)
    if (Files.exists(Paths.get(cachedFilePath))) {
      val cachedFile = Source.fromFile(cachedFilePath)
      cachedFile.mkString
    } else {
      val dataSource = Source.fromURL(url)
      val dataString = processData(dataSource)
      saveAsFile(dataString, cachedFilePath)
      dataString
    }
  }

  def processData(dataSource: Source): String = dataSource.mkString

  private def saveAsFile(htmlString: String, outputFilePath: String) = {
    val file = new File(outputFilePath)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(htmlString)
    bw.close()
  }
}
