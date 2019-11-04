(ns cljfx.fx.window
  "Part of a public API"
  (:require [cljfx.composite :as composite]
            [cljfx.lifecycle :as lifecycle]
            [cljfx.coerce :as coerce])
  (:import [javafx.stage Window]))

(set! *warn-on-reflection* true)

(def props
  (merge
   (composite/props Window
    :event-dispatcher [:setter lifecycle/scalar]
    :height [:setter lifecycle/scalar :coerce double :default Double/NaN]
    :on-close-request [:setter lifecycle/event-handler :coerce coerce/event-handler]
    :on-hidden [:setter lifecycle/event-handler :coerce coerce/event-handler]
    :on-hiding [:setter lifecycle/event-handler :coerce coerce/event-handler]
    :on-showing [:setter lifecycle/event-handler :coerce coerce/event-handler]
    :on-shown [:setter lifecycle/event-handler :coerce coerce/event-handler]
    :opacity [:setter lifecycle/scalar :coerce double :default 1]
    :user-data [:setter lifecycle/scalar]
    :width [:setter lifecycle/scalar :coerce double :default Double/NaN]
    :x [:setter lifecycle/scalar :coerce double :default Double/NaN]
    :y [:setter lifecycle/scalar :coerce double :default Double/NaN])
   (composite/modern-props Window
      ;;These are javafx 11+
      :force-integer-render-scale [:setter lifecycle/scalar :default false]
      :render-scale-x [:setter lifecycle/scalar :coerce double :default 1]
      :render-scale-y [:setter lifecycle/scalar :coerce double :default 1])))
