import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

class Parser {
    public ArrayList<String> parsed;
    private ArrayList<String> adjectives = new ArrayList<>();
    private ArrayList<String> adverbs = new ArrayList<>();
    private ArrayList<String> nouns = new ArrayList<>();
    private ArrayList<String> verbs = new ArrayList<>();
    private Random random;

    char[] consonant = new char[]{'б','в','г','д','ж','з','к','л','м','й','н','п','р','с','т','ф','х','ш','щ'};

    //слово прочь

    Parser () {
        random = new Random();
        adjectives = parseWordsFromFile("rus_comm_adj.txt");
        adverbs = parseWordsFromFile("rus_comm_adv.txt");
        nouns = parseWordsFromFile("rus_comm_noun.txt");
        verbs = parseWordsFromFile("rus_comm_verb.txt");
    }

    Parser (int seed) {
        random = new Random(seed);
        adjectives = parseWordsFromFile("rus_comm_adj.txt");
        adverbs = parseWordsFromFile("rus_comm_adv.txt");
        nouns = parseWordsFromFile("rus_comm_noun.txt");
        verbs = parseWordsFromFile("rus_comm_verb.txt");
    }

    String generateSentence() {
        StringBuilder sentence = new StringBuilder();
        String firstWord = getRandomAdv();
        String thirdWord = getRandomNoun();
        String secondWord = adjEndChanger(getRandomAdj(), thirdWord);
        String fourthWord = verbEndChanger(getRandomVerb());
        sentence.append(Character.toUpperCase(firstWord.charAt(0)))
                .append(firstWord.substring(1))
                .append(" ")
                .append(secondWord)
                .append(" ")
                .append(thirdWord)
                .append(" ")
                .append(fourthWord)
                .append(".");
        return sentence.toString();
    }

    private ArrayList<String> parseWordsFromFile(String fileName) {
        ArrayList<String> words = new ArrayList<>();
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(fileName));
            String temp;
            do {
                temp = reader.readLine();
                if (temp == null) break;
                words.add(temp);
            } while (true);
        } catch (IOException e) {
            System.out.println(e);
        }
        return words;
    }

    String getRandomAdj() {
        return adjectives.get(random.nextInt(adjectives.size() - 1));
    }

    String getRandomNoun() {
        return nouns.get(random.nextInt(nouns.size() - 1));
    }

    String getRandomVerb() {
        return verbs.get(random.nextInt(verbs.size() - 1));
    }

    String getRandomAdv() {
        return adverbs.get(random.nextInt(adverbs.size() - 1));
    }

    String adjEndChanger(String adverb, String noun) {
        String nounEnd = noun.substring(noun.length() - 2);
        String cuttedAdverb = adverb.substring(0, adverb.length() - 2);

        if (isNounFemale(noun)) {
            if (noun.length() > 4) {
                if (noun.substring(noun.length() - 4).equals("нний") || noun.substring(noun.length() - 3).equals("ний"))
                    return cuttedAdverb + "яя";
            }
            return cuttedAdverb + "ая";
        }
        if (isNounNeuter(noun))
            return cuttedAdverb + "ое";
        if (isNounMale(noun))
            return adverb;
        return adverb;
    }

    String verbEndChanger(String verb) {
        int l = verb.length();
        if (verb.substring(l - 4).equals("нуть"))
            return verb.substring(0, l - 4) + "ает";
        if (verb.substring(l - 4).equals("тить"))
            return verb.substring(0, l - 4) + "кает";
        if (verb.substring(l - 3).equals("ать") || verb.substring(l - 3).equals("ить") || verb.substring(l - 3).equals("еть"))
            return verb.substring(0, l - 3) + "ает";
        if (verb.substring(l - 3).equals("ять"))
            return verb.substring(0, l - 3) + "яет";
        if (verb.substring(l - 4).equals("ться"))
            return verb.substring(0, l - 4) + "ется";
        return verb;
    }



    boolean isNounMale(String noun) {
        for (char i : consonant)
            return noun.charAt(noun.length() - 1) == i;
        return noun.substring(noun.length() - 2).equals("ль");
    }

    boolean isNounFemale(String noun) {
        int l = noun.length();
        return noun.charAt(l - 1) == 'а'
                || noun.charAt(l - 1) == 'я'
                || noun.substring(l - 2).equals("сь")
                || noun.substring(l - 2).equals("ть")
                || noun.substring(l - 3).equals("ель");
    }

    boolean isNounNeuter(String noun) {
        return noun.charAt(noun.length() - 1) == 'о'
                || noun.charAt(noun.length() - 1) == 'е';
    }

//    boolean isAdj(String word) {
//        String ending;
//        if (word.length() > 4) {
//            ending = word.substring(word.length() - 2);
//            return ending.equals("ий") || ending.equals("ый");
//        }
//        return false;
//    }
//    boolean isVerb(String word) {
//        String[] exceptions = {"тринадцать"};
//        String ending;
//        if (word.length() > 4) {
//            ending = word.substring(word.length() - 3);
//            return ending.equals("ать")
//                    || ending.equals("ься")
//                    || ending.equals("еть")
//                    || ending.equals("ить")
//                    || ending.equals("уть");
//        }
//        return false;
//    }

    String adjFemaleEnding (String word) {
        String noGender = word.substring(0, word.length() - 2);

        if (word.charAt(word.length() - 3) == 'н') {
            return noGender + "ая";
        }
        return noGender + "ая";
    }

    String adjNeuterEnding (String word) {
        String noGender = word.substring(0, word.length() - 2);

        if (word.charAt(word.length() - 3) == 'щ'
                || word.charAt(word.length() - 3) == 'ш') {
            return noGender + "ее";
        }
        return noGender + "ое";
    }
}



