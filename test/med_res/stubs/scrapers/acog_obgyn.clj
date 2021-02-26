(ns med-res.stubs.scrapers.acog-obgyn)

(def extract-state-programs-return
  [{:name  "Columbia University Irving Medical Center"
    :url   "https://www.cuimc.columbia.edu/"
    :state "New York"}
   {:name  "WellSpan Health"
    :url   "https://www.wellspanmedicaleducation.org"
    :state "Pennsylvania"}])

(defn extract-state-programs [driver state-programs]
  extract-state-programs-return)

(def get-state-name-return
  "Maryland")

(defn get-state-name [driver state-programs]
  get-state-name-return)

(def extract-program-details-return
  {:name  "National Capital Consortium",
   :url   "https://www.usuhs.edu/obg/obg-mil-residency",
   :state get-state-name-return})

(defn extract-program-details [driver program-element state]
  extract-program-details-return)
