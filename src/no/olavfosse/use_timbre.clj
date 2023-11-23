(ns no.olavfosse.use-timbre
  (:require [taoensso.timbre :as tim]))

(defn- select-keys-by-ns [m ns]
  (select-keys m (filter #(= (namespace %) ns) (keys m))))

(defn use-timbre
  "use-timbre is the entire API of this package."
  [ctx]
  (let [config-before tim/*config*]
    ;; Not sure if I want to use the merge strategy of
    ;; `tim/merge-config!`. I'll have to experiment and see if it's
    ;; appropriate or not.
    (tim/merge-config! (update-keys (select-keys-by-ns ctx "timbre") (comp keyword name)))
    (update ctx :biff/stop conj #(tim/set-config! config-before))))
