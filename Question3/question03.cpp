#include<bits/stdc++.h>
using namespace std;
#define Hash_value 37
#define Max 100

int forward[Max];
int backward[Max];

int stream[2];

void forward_Hash(string data,int N)
{
    int temp = 0;
    for (int x = 0; x < N; x++)
    {
        temp += data[x] % Hash_value;
        forward[x] = temp;
    }
}

void backward_Hash(string data,int N)
{
    int temp = 0;
    int y = N - 1;
    for (int x = 0; x < N; x++)
    {
        temp += (data[x]*101) % Hash_value;
        backward[y--] = temp;
    }
}

bool isPalindrome(int start,int last,int N)
{
    if((forward[last] - forward[start]) == (backward[start] - backward[last]))
        return true;
    else
        return false;
}

void findInteger(string str) 
{
    int x = 0;
    stringstream ss;     

    ss << str; 
  
    string temp; 
    int found; 
    while (!ss.eof()) { 
        ss >> temp; 

        if (stringstream(temp) >> found) 
            stream[x++] = found; 
  
        temp = ""; 
    } 
} 

int main()
{
    string data;
    int Q,i=0;
    
    cout << "Enter the String : -";
    getline(cin, data);
    cout << "Enter the Number of Queries : ";
    cin >> Q;
    string query[Q];
    bool result[Q];
    int length = data.length();
    forward_Hash(data, length);
    backward_Hash(data, length);

    while(i < Q)
    {
        cout << "Enter query [ starting_index , ending_index ] :";
        cin >> query[i];
        i++;
    }

    int start, last;
    int temp;

    for (int x = 0; x < Q;x++)
    {
        findInteger(query[x]);
        start = stream[0];
        last = stream[1];
        temp = isPalindrome(start, last, length);
        result[temp];
    }
    for (int x = 0; x < Q;x++)
    {
        cout << query[x] << " - ";
		if (result[x]) cout << "is a Palindrome" <<endl;
		else cout << "is not a Palindrome" << endl;
    }

    return 0;
}
