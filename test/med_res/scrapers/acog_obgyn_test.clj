(ns med-res.scrapers.acog-obgyn-test
  (:require [clojure.test :refer [deftest is]]
            [clj-fakes.core :as f]
            [med-res.scrapers.acog-obgyn :as sut]
            [med-res.mocks.scrapers.acog-obgyn :as sutm]
            [med-res.stubs.scrapers.acog-obgyn :as suts]
            [etaoin.api :as et]
            [med-res.mocks.external.etaoin-api :as etm]
            [med-res.stubs.external.etaoin-api :as ets]))

(deftest scrape-has-correct-invocation-chain
  (f/with-fakes
    (let [sut-cspl (f/recorded-fake sutm/collect-states-program-lists)
          sut-esp  (f/recorded-fake sutm/extract-states-programs)]
      (with-redefs [sut/collect-states-program-lists sut-cspl
                    sut/extract-states-programs      sut-esp]
        (sut/scrape :driver)
        (f/were-called-in-order
          sut-cspl [:driver]
          sut-esp  [:driver :states-program-lists])))))

(deftest collect-states-program-lists-has-correct-invocation-chain
  (f/with-fakes
    (let [et-g   (f/recorded-fake etm/go)
          et-wv  (f/recorded-fake etm/wait-visible)
          et-qa  (f/recorded-fake etm/query-all)]
      (with-redefs [et/go           et-g
                    et/wait-visible et-wv
                    et/query-all    et-qa]
        (sut/collect-states-program-lists :driver)
        (f/were-called-in-order
          et-g  [:driver sut/acog-url]
          et-wv [:driver {:css ".site-footer"}]
          et-qa [:driver {:css ".accordion > li"}])))))

(deftest extract-states-programs-has-correct-return
  (let [states-programs [[] []]
        expected        (concat suts/extract-state-programs-return
                                suts/extract-state-programs-return)]
    (with-redefs [sut/extract-state-programs suts/extract-state-programs]
      (->> states-programs
           (sut/extract-states-programs :driver)
           (= expected)
           is))))

(deftest extract-state-programs-has-correct-return
  (let [expected [suts/extract-program-details-return
                  suts/extract-program-details-return]]
    (with-redefs [sut/get-state-name          suts/get-state-name
                  sut/extract-program-details suts/extract-program-details
                  et/children                 ets/children]
      (->> :state-programs
           (sut/extract-state-programs :driver)
           (= expected)
           is))))

(deftest get-state-name-has-correct-invocation-chain
  (f/with-fakes
    (let [et-c     (f/recorded-fake etm/child)
          et-geihe (f/recorded-fake etm/get-element-inner-html-el)]
      (with-redefs [et/child                      et-c
                    et/get-element-inner-html-el  et-geihe]
        (sut/get-state-name :driver :state-list)
        (f/were-called-in-order
          et-c      [:driver :state-list {:css ".accordion-trigger"}]
          et-geihe  [:driver ets/child-return])))))

(deftest extract-program-details-has-correct-return
  (let [name  "UCLA Medical Center"
        url   "http://obgyn.ucla.edu/body.cfm?id=117"
        state "California"]
    (with-redefs [et/get-element-inner-html-el  (fn [_ _] name)
                  et/get-element-attr-el        (fn [_ _ _] url)]
      (->> state
           (sut/extract-program-details :driver :element)
           (= {:name  name
               :url   url
               :state state})
           is))))
