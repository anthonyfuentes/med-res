(ns med-res.mocks.external.etaoin-api
  (:require [clj-fakes.core :as f]
            [med-res.stubs.external.etaoin-api :as stubs]))

(def go
  [[(f/arg any?) (f/arg string?)] (fn [driver url] nil)])

(def wait-visible
  [[(f/arg any?) (f/arg map?)] (fn [driver query] nil)])

(def query-all
  [[(f/arg any?) (f/arg map?)] (fn [driver query] [])])

(def child
  [[(f/arg any?) (f/arg any?) (f/arg any?)] stubs/child])

(def get-element-inner-html-el
  [[(f/arg any?) (f/arg any?)] (fn [driver element] [])])
