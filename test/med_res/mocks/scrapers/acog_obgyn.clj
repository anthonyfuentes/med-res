(ns med-res.mocks.scrapers.acog-obgyn
  (:require [clj-fakes.core :as f]))

(def collect-states-program-lists
  [[(f/arg any?)] (fn [driver] :states-program-lists)])

(def extract-states-programs
  [[(f/arg any?) (f/arg any?)] (fn [driver states-programs] [])])
