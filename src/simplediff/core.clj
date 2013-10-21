(ns simplediff.core)

(use 'simplediff.diff)

(defn -main [& args]
  (if (= 2 (count args))
    (doseq [i (dodiff (make (first args) (second args)))]
      (println i))
    (println "usage: simplediff <file1> <file2>")))
