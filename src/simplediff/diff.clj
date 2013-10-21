(ns simplediff.diff
  (:use clojure.java.io))

(defrecord FileDiff [fileA fileB])

(defn make [a b] (FileDiff. a b))


(defn exists-line? [line lines]
  (if (empty? lines)
    false
    (if (= line (first lines)) true
        (recur line (rest lines)))))


(defn docompare[linesA cur-line-noA linesB cur-line-noB]
  (let [cur-line-noA++ (inc cur-line-noA)
        cur-line-noB++ (inc cur-line-noB)
        carA (first linesA)
        carB (first linesB)
        cdrA (rest linesA)
        cdrB (rest linesB)
        str> #(str "> " %)
        str< #(str "< " %)
        adiff #(list* %1 "a" %2 [(str> %3)] )
        ddiff #(list* %1 "d" %2 [(str< %3)] )
        cdiff #(list* %1 "c" %2 (str< %3) [(str> %4)] )         
        ]
    (cond
   (and (empty? linesA) (empty? linesB)) nil
   (empty? linesA) (cons (adiff cur-line-noA cur-line-noB++ carB)
                         (docompare linesA cur-line-noA cdrB cur-line-noB++))

     (empty? linesB) (cons (ddiff cur-line-noA++ cur-line-noB carA)
                         (docompare cdrA cur-line-noA++ linesB cur-line-noB))

   (= carA carB) (docompare cdrA cur-line-noA++ cdrB cur-line-noB++)
   (exists-line? carB cdrA) (cons (ddiff cur-line-noA++ cur-line-noB carA) (docompare cdrA cur-line-noA++ linesB cur-line-noB))
   (exists-line? carA cdrB) (cons (adiff cur-line-noA cur-line-noB++ carB) (docompare linesA cur-line-noA cdrB cur-line-noB++))
   :else (cons (cdiff cur-line-noA++ cur-line-noB++ carA carB) (docompare cdrA cur-line-noA++ cdrB cur-line-noB++))
   ))
  )

 
(defn dodiff [D]
  (let [rdrA (reader (:fileA D))
        rdrB (reader (:fileB D))
        linesA (line-seq rdrA)
        linesB (line-seq rdrB)
        ]
    (docompare linesA 0 linesB 0)))
  
