(ns med-res.csv
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv]))

(defn write [data path]
  (with-open [writer (io/writer path)]
    (->> data
         (map vals)
         (csv/write-csv writer))))
