(ns simplediff.diff
  (:use clojure.java.io))

;; Tests whether line exits in lines list
;; > (exists-line? "first line" ["other lines" "some lines"])
;; > false
;;
(defn exists-line? [line lines]
  (if (empty? lines)
    false
    (if (= line (first lines)) true
        (recur line (rest lines)))))

;; Do compare for all lines in linesA with all lines in linesB.
;; cur-line-noA and cur-line-noB serve as line number references
;; for generating the specific line numbers in the difference return value
(defn docompare[linesA cur-line-noA linesB cur-line-noB]
  (let [cur-line-noA++ (inc cur-line-noA)
        cur-line-noB++ (inc cur-line-noB)
        carA (first linesA)
        carB (first linesB)  ; car refers to first element of list
        cdrA (rest linesA)
        cdrB (rest linesB)   ; cdr refers to list without first element 
        str> #(str "> " %)
        str< #(str "< " %)
        adiff #(list* %1 "a" %2 [(str> %3)] ) ; Add difference
        ddiff #(list* %1 "d" %2 [(str< %3)] ) ; Delete difference
        cdiff #(list* %1 "c" %2 (str< %3) [(str> %4)] ) ; Change difference     
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

;; Top-level method of namespace.  D is a Diff object
(defn dodiff [D]
  (let [rdrA (reader (:fileA D))
        rdrB (reader (:fileB D))
        linesA (line-seq rdrA)
        linesB (line-seq rdrB)
        ]
    (docompare linesA 0 linesB 0)))
