(ns project-guess-number.core
  (:gen-class))

(defn get-random-number [min-value max-value]
  (+ (rand-int (- max-value min-value 1)) min-value))
(defn -main [& args]
  (println "Welcome to Guess the Number!")
  (print "Please enter your name: ")
  (flush)
  (let [player-name (read-line)
        min-value 1
        max-value 100
        target (get-random-number min-value max-value)
        start-time (System/currentTimeMillis)]
    (loop [attempts 0
           game-data {:name player-name
                      :attempts 0}]
      (if (= attempts 10)
        (do
          (println "You've run out of attempts! The number was" target)
          (println "Game Over!"))
        (let [guess (read-line)]
          (if (Integer/parseInt guess)
            (let [guess (Integer/parseInt guess)
                  new-attempts (inc attempts)]
              (cond
                (< guess target) (do
                                   (println "Too low!")
                                   (recur new-attempts game-data))
                (> guess target) (do
                                   (println "Too high!")
                                   (recur new-attempts game-data))
                :else (do
                        (let [end-time (System/currentTimeMillis)
                              elapsed-time (- end-time start-time)]
                          (println "Congratulations," player-name "! You guessed the number in" new-attempts "attempts!")
                          (println "Elapsed time:", elapsed-time "milliseconds")
                          (let [updated-game-data (assoc game-data :attempts new-attempts
                                                         :elapsed-time-milliseconds elapsed-time)]
                            (println "Game Over!")
                            (println "Game data:", updated-game-data))))))))))))
