(ns med-res.scrapers.acog-obgyn
  (:require [etaoin.keys]
            [etaoin.api :refer :all]))

(def acog-url
  (str "https://www.acog.org/career-support"
       "/medical-students/medical-student-toolkit"
       "/ob-gyn-residencies-by-state"))

(defn extract-program-details [driver program-element state]
  {:name  (get-element-inner-html-el driver program-element)
   :url   (get-element-attr-el driver program-element :href)
   :state state})

(defn get-state-name [driver state-list]
  (->> {:css ".accordion-trigger"}
       (child driver state-list)
       (get-element-inner-html-el driver)))

(defn extract-state-programs [driver state-programs]
  (let [state (get-state-name driver state-programs)]
    (map #(extract-program-details driver % state)
         (children driver state-programs {:css "a"}))))

(defn extract-states-programs [driver states-programs]
  (mapcat
    #(extract-state-programs driver %)
    states-programs))

(defn collect-states-program-lists [driver]
  (go           driver acog-url)
  (wait-visible driver {:css ".site-footer"})
  (query-all    driver {:css ".accordion > li"}))

(defn scrape [driver]
  (->> driver
       collect-states-program-lists
       (extract-states-programs driver)))
