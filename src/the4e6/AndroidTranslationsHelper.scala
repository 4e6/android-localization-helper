package the4e6

import java.io.File
import scala.xml._

/** Helps to find missing or obsolete translations of localized resources  
 *  in android project. */
object AndroidTranslationsHelper {
  def main(args: Array[String]) {
    if (args.isEmpty) {
      exit(helpMsg)
    }

    /** Android project root folder. */
    val project = new File(args(0))
    if (!project.exists) {
      exit("folder not exists")
    }

    /** Try to find original strings.xml file in 'values' folder. */
    val stringsOrig = findStrings(project, "values$")
    if (stringsOrig.isEmpty) {
      exit("res/values/strings.xml not found")
    }

    /** Try to find localized strings.xml file in 'values*' folders. */
    val stringsLocalized =
      findStrings(project, "values.") sortWith { _.getParent < _.getParent }
    if (stringsLocalized.isEmpty) {
      exit("localized resources not found")
    }

    /** Original XML resources from values/strings.xml */
    val rOrig: NodeSeq = XML.loadFile(stringsOrig.head) \\ "resources"

    stringsLocalized.foreach(f => {
      val r = XML.loadFile(f) \\ "resources"
      printNiceName(f)
      val extraLine_? =
        resTypes.map(t => {
          val names = mkNamesSet(r, t)
          val namesOrig = mkNamesSet(rOrig, t)
          printDiffs(t, namesOrig diff names, names diff namesOrig)
        }) contains true
      if (extraLine_?) println
    })
  }

  /** Returns array of all string.xml files according to
   *  'dir'/res/'regex'/strings.xml pattern. */
  def findStrings(dir: File, regex: String): Array[File] =
    dir.listFiles
      .withFilter { _.getName == "res" }
      .flatMap { _.listFiles.filter(f => regex.r.findFirstIn(f.getName).isDefined) }
      .flatMap { _.listFiles.filter(_.getName == "strings.xml") }

  /** Returns a set of resource names. */
  def mkNamesSet(ns: NodeSeq, tag: String): Set[String] =
    (ns \ tag) map { _ \ "@name" text } toSet

  def printNiceName(f: File) {
    println(f.getParentFile.getName + "/" + f.getName)
  }

  /**
   * Print diff section for given resource type.
   *
   *  @return flag, if there is a need for extra blank line after output */
  def printDiffs(resType: String, t: Set[String], o: Set[String]): Boolean = {
    if (t.nonEmpty || o.nonEmpty) {
      println(" " + resType)
      t map { "  [T] " + _ } foreach println
      o map { "  [O] " + _ } foreach println
      true
    } else false
  }

  def exit(msg: String) {
    println(msg)
    System.exit(0)
  }

  /** List of android resource types. */
  val resTypes = "string" :: "string-array" :: "plurals" :: Nil

  val helpMsg =
    "Helps to find missing or obsolete translations for android resources\n" +
    "\n" +
    "Usage: You must specify android project folder\n" +
    "`alh.sh /path/to/android/project'\n" +
    "\n" +
    "Output:\n" +
    "[filename]\n" +
    " *[resource type]\n" +
    "  *[T] [resource name]\n" +
    "  *[O] [resource name]\n" +
    "\n" +
    "[T] Resources need to be translated (exists in original xml but not in localized version)\n" +
    "[O] Obsolete resources (exists in localized version but not in original one)\n"
}