
// return sebuah mutable list untuk menandakan semua huruf / letter belum ditebak
// contoh: _ _ _ _ _ _

fun getFirstResult(wordList: String): MutableList<String> {
    val list = arrayListOf<String>()
    for (index in 1..wordList.length) {
        list.add("_")
    }
    return list
}

// return sebuah mutable list yang nilainya berubah sesuai dengan huruf yang sudah ditebak
// jika huruf yang ditebak ada pada wordList, maka huruf tersebut akan ditambahkan ke newList
// selain itu tambahkan _ (underscore)
// contoh: k o _ l _ n

fun getNewList(wordList: MutableList<String>, guessLetter: String, currentList: MutableList<String>): MutableList<String> {
    val newList = arrayListOf<String>()
    for (index in wordList.indices) {
        if (wordList[index].toLowerCase() == guessLetter.toLowerCase()) {
            newList.add(wordList[index])
        } else {
            newList.add(currentList[index])
        }
    }
    return newList
}

fun main() {

    // game berjalan selama wrongUserGuess adalah true
    // wrongUserGuessValue: berapa kali user salah menebak

    var wrongUserGuess = true
    var wrongUserGuessValue = 0

    print("Enter a word\t: ")
    val guessWordGame = readLine()!!

    // gameWordList: berisi mutable list dari setiap huruf yang diinputkan
    // contoh: [K, o, t, l, i, n] -> string

    val gameWordList = guessWordGame.split("").filter { it != "" }.toMutableList()

    // return getFirstResult

    val firstGuessResult = getFirstResult(guessWordGame)

    // tampilan getFirstResult dan value dari wrong guess

    print("\nCurrent result\t: ")
    firstGuessResult.forEach { print("$it ") }
    println("\n# Wrong guess\t: $wrongUserGuessValue\n")

    // buat variable currentResult yang menampung value dari firstGuessResult berupa: _ _ _ _ _ _
    // tujuannya adalah agar setiap value yang sudah ditebak bisa diubah nilainya

    var currentResult = getNewList(gameWordList, "", firstGuessResult)

    while (wrongUserGuess) {

        // user menginputkan letter guess

        print("Enter letter guess\t: ")
        val letterGuess = readLine()!!

        // jika yang diinputkan user tidak ada di dalam guessWordGame
        // maka wrongUserGuessValue ditambah satu

        if (letterGuess.toLowerCase() !in guessWordGame.toLowerCase()) {
            wrongUserGuessValue++
        }

        // ubah currentResult sesuai dengan value / letter yang sudah ditebak

        currentResult = getNewList(gameWordList, letterGuess, currentResult)

        // tampilkan kembali semua huruf yang sudah ditebak
        // tampilkan juga berapa kali user salah dalam menebak

        print("\nCurrent result\t: ")
        currentResult.forEach { print("$it ") }
        println("\n# Wrong guess\t: $wrongUserGuessValue\n")

        // jika currentResult sudah tidak menampung _ (underscore) lagi
        // maka game selesai

        if (!currentResult.contains("_")) {
            wrongUserGuess = false
        }
    }

    println("Well done!")
}