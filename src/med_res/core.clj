(ns med-res.core
  (:require [etaoin.api                   :refer [firefox]]
            [med-res.scrapers.acog-obgyn  :as acogob]
            [med-res.csv                  :as csv]))

(defn -main [path]
  (-> (firefox)
      acogob/scrape
      (csv/write path)))
