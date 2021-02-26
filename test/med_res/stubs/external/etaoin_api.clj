(ns med-res.stubs.external.etaoin-api
  (:require [clj-fakes.core :as f]))

(def children-return
  [:child :child])

(defn children [driver element query]
  children-return)

(def child-return
  :child)

(defn child [driver element query]
  child-return)
