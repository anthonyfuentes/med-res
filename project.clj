(defproject med-res "0.1.0-SNAPSHOT"
  :description  "Collect medical residency data"
  :main         med-res.core
  :repl-options {:init-ns med-res.core}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [etaoin "0.4.1"]
                 [clj-fakes "0.12.0"]
                 [org.clojure/data.csv "1.0.0"]]

  :profiles {:dev           [:project/dev]
             :test          [:project/dev :project/test]
             :project/test  {}
             :project/dev   {:plugins [[com.jakemccrary/lein-test-refresh "0.24.1"]]}})
