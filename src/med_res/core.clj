(ns med-res.core)

(defn -main [x]
  (->> x
       (str "Received: ")
       println))
