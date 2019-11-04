(ns cljfx.version)

(defonce version
  (if (try (slurp (clojure.string/join
                   (System/getProperty "file.separator")
                   [(System/getProperty "java.home")
                    "lib"
                    "javafx.properties"]))
           (catch Exception e nil))
    :eight
    :modern))
