(ns simplediff.core (:gen-class))

(use 'simplediff.diff)

(defrecord FileDiff [fileA fileB])

(defn make [a b] (FileDiff. a b))

(defn -main [& args]
  (if (= 2 (count args))
    (doseq [i (dodiff (make (first args) (second args)))]
      (println i))
    (println "usage: simplediff <file1> <file2>")))
