/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.lenovo.vandan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    public ArrayList<String> wordlist;
    public HashMap<String, ArrayList<String>> letterstoWords;
    public HashSet<String> wordSet;

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        wordlist=new ArrayList<String>();
        letterstoWords=new HashMap<>();
        wordSet=new HashSet<>();
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordlist.add(word);
            wordSet.add(word);
            String sortedWord=sortLetters(word);
            if(letterstoWords.containsKey(sortedWord))
            {
                letterstoWords.get(sortedWord).add(word);
            }
            else {
                 ArrayList<String> newal=new ArrayList<>();
                 newal.add(word);
                letterstoWords.put(sortedWord,newal);
            }
        }
    }

    public boolean isGoodWord(String word, String base) {
        return (wordSet.contains(word)&& word.contains(base));
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        String sortedWord=sortLetters(targetWord);

        for(int i=0;i<wordlist.size();i++) {
            if (sortLetters(wordlist.get(i)).length()==sortedWord.length() && sortedWord.equalsIgnoreCase(sortLetters(wordlist.get(i))))
            {
                result.add(wordlist.get(i));
            }
        }
        return result;
    }

    public String sortLetters(String currword)
    {
        char letters[]=currword.toCharArray();
        char temp;
        for(int i=0;i<letters.length;i++)
        {
            for(int j=0;j<letters.length-i-1;j++)
            {
                if(letters[j]>=letters[j+1])
                {
                  temp=letters[j];
                    letters[j]=letters[j+1];
                    letters[j+1]=temp;
                }
            }
        }
        String sortedWord=new String(letters);
        return(sortedWord);
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        String wordWOML;
        String sortedwordWOML;
        for(char i='a';i<='z';i++)
        {
            wordWOML=word+i;
            sortedwordWOML=sortLetters(wordWOML);
            if(letterstoWords.containsKey(sortedwordWOML))
            {
                ArrayList<String> alforwordWOML=letterstoWords.get(sortedwordWOML);
                for(String element:alforwordWOML){
                    result.add(element);
                }
            }
        }
        return result;
    }

    public String pickGoodStarterWord() {
        return "stop";
    }
}
