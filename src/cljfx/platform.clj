(ns cljfx.platform
  "Part of a public API"
  (:require [cljfx.version :as v])
  (:import [javafx.application Platform]))

(defonce force-toolkit-init
  (case v/version
    :eight (javafx.embed.swing.JFXPanel.)))

(defmacro run-later [& body]
  `(let [*result# (promise)]
     (Platform/runLater
       (fn []
         (let [result# (try
                         [nil (do ~@body)]
                         (catch Exception e#
                           [e# nil]))
               [err# ~'_] result#]
           (deliver *result# result#)
           (when err#
             (.printStackTrace ^Throwable err#)))))
     (delay
       (let [[err# val#] @*result#]
         (if err#
           (throw err#)
           val#)))))

(defmacro on-fx-thread [& body]
  `(if (Platform/isFxApplicationThread)
     (deliver (promise) (do ~@body))
     (run-later ~@body)))

(defn initialize []
  (case v/version
    :eight (on-fx-thread (Platform/setImplicitExit false))
    (try
      (.startup Platform #(Platform/setImplicitExit false))
      ::initialized
      (catch IllegalStateException _
        ::already-initialized))))
