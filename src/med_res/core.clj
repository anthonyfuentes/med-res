(ns med-res.core
  (:require [etaoin.api                   :refer [firefox]]
            [med-res.scrapers.acog-obgyn  :as acogob]
            [clojure.pprint               :refer [pprint]]))

(defn -main []
  (-> (firefox)
      acogob/scrape
      pprint))
