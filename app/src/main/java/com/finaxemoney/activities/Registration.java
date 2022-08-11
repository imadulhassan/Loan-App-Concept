package com.finaxemoney.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.finaxemoney.R;
import com.finaxemoney.fragment.LoanFragment;
import com.finaxemoney.fragment.RepayFragment;
import com.finaxemoney.model.USER;
import com.finaxemoney.tools.CONSTANT;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText  panNumber , adharNumber , userName,emailAddress;
    String phoneNumber;
    ProgressBar bar;
    MaterialButton authenticateBtn;
    String uname  , eMail , panNo , AdharNo;
    DatabaseReference userRef;
    FirebaseAuth auth;

    String  AP []={
            "Adilabad",
            "Anantapur",
            "Chittoor",
            "Kakinada",
            "Guntur",
            "Hyderabad",
            "Karimnagar",
            "Khammam",
            "Krishna",
            "Kurnool",
            "Mahbubnagar",
            "Medak",
            "Nalgonda",
            "Nizamabad",
            "Ongole",
            "Hyderabad",
            "Srikakulam",
            "Nellore",
            "Visakhapatnam",
            "Vizianagaram",
            "Warangal",
            "Eluru",
            "Kadapa"
            };

            String Ar []={
            "Anjaw",
            "Changlang",
            "East Siang",
            "Kurung Kumey",
            "Lohit",
            "Lower Dibang Valley",
            "Lower Subansiri",
            "Papum Pare",
            "Tawang",
            "Tirap",
            "Dibang Valley",
            "Upper Siang",
            "Upper Subansiri",
            "West Kameng",
            "West Siang"
            };

           String As []  ={
            "Baksa",
            "Barpeta",
            "Bongaigaon",
            "Cachar",
            "Chirang",
            "Darrang",
            "Dhemaji",
            "Dima Hasao",
            "Dhubri",
            "Dibrugarh",
            "Goalpara",
            "Golaghat",
            "Hailakandi", "Jorhat",
            "Kamrup",
            "Kamrup Metropolitan",
            "Karbi Anglong",
            "Karimganj",
            "Kokrajhar",
            "Lakhimpur",
            "Marigaon",
            "Nagaon",
            "Nalbari",
            "Sibsagar",
            "Sonitpur",
            "Tinsukia",
            "Udalguri"
            };

           String [] br ={
            "Araria",
            "Arwal",
            "Aurangabad",
            "Banka",
            "Begusarai",
            "Bhagalpur",
            "Bhojpur",
            "Buxar",
            "Darbhanga",
            "East Champaran",
            "Gaya",
            "Gopalganj",
            "Jamui",
            "Jehanabad",
            "Kaimur",
            "Katihar",
            "Khagaria",
            "Kishanganj",
            "Lakhisarai",
            "Madhepura",
            "Madhubani",
            "Munger",
            "Muzaffarpur",
            "Nalanda",
            "Nawada",
            "Patna",
            "Purnia",
            "Rohtas",
            "Saharsa",
            "Samastipur",
            "Saran",
            "Sheikhpura",
            "Sheohar",
            "Sitamarhi",
            "Siwan",
            "Supaul",
            "Vaishali",
            "West Champaran",
            "Chandigarh"
            };

            String [] CG ={
            "Bastar",
            "Bijapur",
            "Bilaspur",
            "Dantewada",
            "Dhamtari",
            "Durg",
            "Jashpur",
            "Janjgir-Champa",
            "Korba",
            "Koriya",
            "Kanker",
            "Kabirdham (Kawardha)",
            "Mahasamund",
            "Narayanpur",
            "Raigarh",
            "Rajnandgaon",
            "Raipur",
            "Surguja"
            };

          String [] Dn ={
            "Dadra and Nagar Haveli"
            };

           String [] Dd={
            "Daman",
            "Diu"
            };


            String [] dl={
            "Central Delhi",
            "East Delhi",
            "New Delhi",
            "North Delhi",
            "North East Delhi",
            "North West Delhi",
            "South Delhi",
            "South West Delhi",
            "West Delhi"
            };

           String ga[] ={
            "North Goa",
            "South Goa"
            };

           String [] GJ={
            "Ahmedabad",
            "Amreli district",
            "Anand",
            "Banaskantha",
            "Bharuch",
            "Bhavnagar",
            "Dahod",
            "The Dangs",
            "Gandhinagar",
            "Jamnagar",
            "Junagadh",
            "Kutch",
            "Kheda",
            "Mehsana",
            "Narmada",
            "Navsari",
            "Patan",
            "Panchmahal",
            "Porbandar",
            "Rajkot",
            "Sabarkantha",
            "Surendranagar",
            "Surat",
            "Vyara",
            "Vadodara",
            "Valsad"
            };

            String [] hr  ={
            "Ambala",
            "Bhiwani",
            "Faridabad",
            "Fatehabad",
            "Gurgaon",
            "Hissar",
            "Jhajjar",
            "Jind",
            "Karnal",
            "Kaithal",
            "Kurukshetra",
            "Mahendragarh",
            "Mewat",
            "Palwal",
            "Panchkula",
            "Panipat",
            "Rewari",
            "Rohtak",
            "Sirsa",
            "Sonipat",
            "Yamuna Nagar"
            };

            String []  Hp ={
            "Bilaspur",
            "Chamba",
            "Hamirpur",
            "Kangra",
            "Kinnaur",
            "Kullu",
            "Lahaul and Spiti",
            "Mandi",
            "Shimla",
            "Sirmaur",
            "Solan",
            "Una"
            };

            String [] JmK={
            "Anantnag",
            "Badgam",
            "Bandipora",
            "Baramulla",
            "Doda",
            "Ganderbal",
            "Jammu",
            "Kargil",
            "Kathua",
            "Kishtwar",
            "Kupwara",
            "Kulgam",
            "Leh",
            "Poonch",
            "Pulwama",
            "Rajauri",
            "Ramban",
            "Reasi",
            "Samba",
            "Shopian",
            "Srinagar",
            "Udhampur",
            };

            String [] jh ={
            "Bokaro",
            "Chatra",
            "Deoghar",
            "Dhanbad",
            "Dumka",
            "East Singhbhum",
            "Garhwa",
            "Giridih",
            "Godda",
            "Gumla",
            "Hazaribag",
            "Jamtara",
            "Khunti",
            "Koderma",
            "Latehar",
            "Lohardaga",
            "Pakur",
            "Palamu",
            "Ramgarh",
            "Ranchi",
            "Sahibganj",
            "Seraikela Kharsawan",
            "Simdega",
            "West Singhbhum"
            };


      String [] Ka ={
            "Bagalkot",
            "Bangalore Rural",
            "Bangalore Urban",
            "Belgaum",
            "Bellary",
            "Bidar",
            "Bijapur",
            "Chamarajnagar",
            "Chikkamagaluru",
            "Chikkaballapur",
            "Chitradurga",
            "Davanagere",
            "Dharwad",
            "Dakshina Kannada",
            "Gadag",
            "Gulbarga",
            "Hassan",
            "Haveri district",
            "Kodagu",
            "Kolar",
            "Koppal",
            "Mandya",
            "Mysore",
            "Raichur",
            "Shimoga",
            "Tumkur",
            "Udupi",
            "Uttara Kannada",
            "Ramanagara",
            "Yadgir"
            };
              String  [] Kl={
            "Alappuzha",
            "Ernakulam",
            "Idukki",
            "Kannur",
            "Kasaragod",
            "Kollam",
            "Kottayam",
            "Kozhikode",
            "Malappuram",
            "Palakkad",
            "Pathanamthitta",
            "Thrissur",
            "Thiruvananthapuram",
            "Wayanad"
            };

            String [] Mp={
            "Alirajpur",
            "Anuppur",
            "Ashok Nagar",
            "Balaghat",
            "Barwani",
            "Betul",
            "Bhind",
            "Bhopal",
            "Burhanpur",
            "Chhatarpur",
            "Chhindwara",
            "Damoh",
            "Datia",
            "Dewas",
            "Dhar",
            "Dindori",
            "Guna",
            "Gwalior",
            "Harda",
            "Hoshangabad",
            "Indore",
            "Jabalpur",
            "Jhabua",
            "Katni",
            "Khandwa (East Nimar)",
            "Khargone (West Nimar)",
            "Mandla",
            "Mandsaur",
            "Morena",
            "Narsinghpur",
            "Neemuch",
            "Panna",
            "Rewa",
            "Rajgarh",
            "Ratlam",
            "Raisen",
            "Sagar",
            "Satna",
            "Sehore",
            "Seoni",
            "Shahdol",
            "Shajapur",
            "Sheopur",
            "Shivpuri",
            "Sidhi",
            "Singrauli",
            "Tikamgarh",
            "Ujjain",
            "Umaria",
            "Vidisha"
            };


           String [] Mh={
            "Ahmednagar",
            "Akola",
            "Amravati",
            "Aurangabad",
            "Bhandara",
            "Beed",
            "Buldhana",
            "Chandrapur",
            "Dhule",
            "Gadchiroli",
            "Gondia",
            "Hingoli",
            "Jalgaon",
            "Jalna",
            "Kolhapur",
            "Latur",
            "Mumbai City",
            "Mumbai suburban",
            "Nandurbar",
            "Nanded",
            "Nagpur",
            "Nashik",
            "Osmanabad",
            "Parbhani",
            "Pune",
            "Raigad",
            "Ratnagiri",
            "Sindhudurg",
            "Sangli",
            "Solapur",
            "Satara",
            "Thane",
            "Wardha",
            "Washim",
            "Yavatmal"
            };
         String []            MN={
            "Bishnupur",
            "Churachandpur",
            "Chandel",
            "Imphal East",
            "Senapati",
            "Tamenglong",
            "Thoubal",
            "Ukhrul",
            "Imphal West"
            };
    String [] ML   ={
            "East Garo Hills",
            "East Khasi Hills",
            "Jaintia Hills",
            "Ri Bhoi",
            "South Garo Hills",
            "West Garo Hills",
            "West Khasi Hills"
            };

          String [] mz ={
            "Aizawl",
            "Champhai",
            "Kolasib",
            "Lawngtlai",
            "Lunglei",
            "Mamit",
            "Saiha",
            "Serchhip",
            };

    String[]  Nl    ={
            "Dimapur",
            "Kohima",
            "Mokokchung",
            "Mon",
            "Phek",
            "Tuensang",
            "Wokha",
            "Zunheboto"
            };

           String [] or  ={
            "Angul",
            "Boudh (Bauda)",
            "Bhadrak",
            "Balangir",
            "Bargarh (Baragarh)",
            "Balasore",
            "Cuttack",
            "Debagarh (Deogarh)",
            "Dhenkanal",
            "Ganjam",
            "Gajapati",
            "Jharsuguda",
            "Jajpur",
            "Jagatsinghpur",
            "Khordha",
            "Kendujhar (Keonjhar)",
            "Kalahandi",
            "Kandhamal",
            "Koraput",
            "Kendrapara",
            "Malkangiri",
            "Mayurbhanj",
            "Nabarangpur",
            "Nuapada",
            "Nayagarh",
            "Puri",
            "Rayagada",
            "Sambalpur",
            "Subarnapur (Sonepur)",
            "Sundergarh"
            };
           String [] pr={
            "Karaikal",
            "Mahe",
            "Pondicherry",
            "Yanam"
            };
           String [] Pun={
            "Amritsar",
            "Barnala",
            "Bathinda",
            "Firozpur",
            "Faridkot",
            "Fatehgarh Sahib",
            "Fazilka",
            "Gurdaspur",
            "Hoshiarpur",
            "Jalandhar",
            "Kapurthala",
            "Ludhiana",
            "Mansa",
            "Moga",
            "Sri Muktsar Sahib",
            "Pathankot",
            "Patiala",
            "Rupnagar",
            "Ajitgarh (Mohali)",
            "Sangrur",
            "Nawanshahr",
            "Tarn Taran"
            };
           String [] rj={
            "Ajmer",
            "Alwar",
            "Bikaner",
            "Barmer",
            "Banswara",
            "Bharatpur",
            "Baran",
            "Bundi",
            "Bhilwara",
            "Churu",
            "Chittorgarh",
            "Dausa",
            "Dholpur",
            "Dungapur",
            "Ganganagar",
            "Hanumangarh",
            "Jhunjhunu",
            "Jalore",
            "Jodhpur",
            "Jaipur",
            "Jaisalmer",
            "Jhalawar",
            "Karauli",
            "Kota",
            "Nagaur",
            "Pali",
            "Pratapgarh",
            "Rajsamand",
            "Sikar",
            "Sawai Madhopur",
            "Sirohi",
            "Tonk",
            "Udaipur"
            };
           String[] sk={
            "East Sikkim",
            "North Sikkim",
            "South Sikkim",
            "West Sikkim"
            };
          String[]  tn={
            "Ariyalur",
            "Chennai",
            "Coimbatore",
            "Cuddalore",
            "Dharmapuri",
            "Dindigul",
            "Erode",
            "Kanchipuram",
            "Kanyakumari",
            "Karur",
            "Madurai",
            "Nagapattinam",
            "Nilgiris",
            "Namakkal",
            "Perambalur",
            "Pudukkottai",
            "Ramanathapuram",
            "Salem",
            "Sivaganga",
            "Tirupur",
            "Tiruchirappalli",
            "Theni",
            "Tirunelveli",
            "Thanjavur",
            "Thoothukudi",
            "Tiruvallur",
            "Tiruvarur",
            "Tiruvannamalai",
            "Vellore",
            "Viluppuram",
            "Virudhunagar",
             };
         String []  tr ={
            "Dhalai",
            "North Tripura",
            "South Tripura",
            "Khowai",
            "West Tripura"
            };
          String [] up={
            "Agra",
            "Allahabad",
            "Aligarh",
            "Ambedkar Nagar",
            "Auraiya",
            "Azamgarh",
            "Barabanki",
            "Budaun",
            "Bagpat",
            "Bahraich",
            "Bijnor",
            "Ballia",
            "Banda",
            "Balrampur",
            "Bareilly",
            "Basti",
            "Bulandshahr",
            "Chandauli",
            "Chhatrapati Shahuji Maharaj Nagar",
            "Chitrakoot",
            "Deoria",
            "Etah",
            "Kanshi Ram Nagar",
            "Etawah",
            "Firozabad",
            "Farrukhabad",
            "Fatehpur",
            "Faizabad",
            "Gautam Buddh Nagar",
            "Gonda",
            "Ghazipur",
            "Gorakhpur",
            "Ghaziabad",
            "Hamirpur",
            "Hardoi",
            "Mahamaya Nagar",
            "Jhansi",
            "Jalaun",
            "Jyotiba Phule Nagar",
            "Jaunpur district",
            "Ramabai Nagar (Kanpur Dehat)",
            "Kannauj",
            "Kanpur",
            "Kaushambi",
            "Kushinagar",
            "Lalitpur",
            "Lakhimpur Kheri",
            "Lucknow",
            "Mau",
            "Meerut",
            "Maharajganj",
            "Mahoba",
            "Mirzapur",
            "Moradabad",
            "Mainpuri",
            "Mathura",
            "Muzaffarnagar",
            "Panchsheel Nagar district (Hapur)",
            "Pilibhit",
            "Shamli",
            "Pratapgarh",
            "Rampur",
            "Raebareli",
            "Saharanpur",
            "Sitapur",
            "Shahjahanpur",
            "Sant Kabir Nagar",
            "Siddharthnagar",
            "Sonbhadra",
            "Sant Ravidas Nagar",
            "Sultanpur",
            "Shravasti",
            "Unnao",
            "Varanasi",
            };
            String [] uk ={
            "Almora",
            "Bageshwar",
            "Chamoli",
            "Champawat",
            "Dehradun",
            "Haridwar",
            "Nainital",
            "Pauri Garhwal",
            "Pithoragarh",
            "Rudraprayag",
            "Tehri Garhwal",
            "Udham Singh Nagar",
            "Uttarkashi"
            };
            String [] wb ={
            "Birbhum",
            "Bankura",
            "Bardhaman",
            "Darjeeling",
            "Dakshin Dinajpur",
            "Hooghly",
            "Howrah",
            "Jalpaiguri",
            "Cooch Behar",
            "Kolkata",
            "Maldah",
            "Paschim Medinipur",
            "Purba Medinipur",
            "Murshidabad",
            "Nadia",
            "North 24 Parganas",
            "South 24 Parganas",
            "Purulia",
            "Uttar Dinajpur",
            };
    String [] state={"West Bengal (WB)","Uttarakhand (UK)",  "Uttar Pradesh (UP)","Tripura (TR)",  "Tamil Nadu (TN)", "Sikkim (SK)", "Rajasthan (RJ)"
            , "Punjab (PB)" , "Pondicherry (PY)","Orissa (OR)","Nagaland (NL)","Mizoram (MZ)", "Meghalaya (ML)","Manipur (MN)","Maharashtra (MH)" , "Madhya Pradesh (MP)" ,"Kerala (KL)"
            ,     "Karnataka (KA)",  "Jharkhand (JH)","Jammu and Kashmir (JK)","Himachal Pradesh (HP)","Haryana (HR)","Gujarat (GJ)","Goa (GA)"
            ,"Delhi (DL)", "Daman and Diu (DD)", "Dadra and Nagar Haveli (DN)","Chhattisgarh (CG)","Bihar (BR)","Assam (AS)","A runachal Pradesh (AR)","Andhra Pradesh (AP)","Select State"};

    String [] city_nameArray={};
  Spinner state_sp , cities_sp ;
  String st_state,st_city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

    }

    void init(){
        //tabs

        //Feilds
        panNumber= findViewById(R.id.panNumber);
        adharNumber= findViewById(R.id.adharNumber);
        userName= findViewById(R.id.username);
        emailAddress= findViewById(R.id.email);
        state_sp= findViewById(R.id.state);
         cities_sp = findViewById(R.id.citilist);

        bar= findViewById(R.id.progressBar);

        //Material Buuton
        authenticateBtn = findViewById(R.id.registerBtn);
        authenticateBtn.setOnClickListener(this);
        List<String> listOfProducts = Arrays.asList(state);
        Collections.reverse(listOfProducts);
        final String[] reversed = listOfProducts.toArray(state);



        userRef= FirebaseDatabase.getInstance().getReference().child(CONSTANT.USER);
        auth= FirebaseAuth.getInstance();
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, reversed);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        state_sp.setAdapter(spinnerArrayAdapter);
        state_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                 if(position!=0){
                     cities_sp.setVisibility(View.VISIBLE);
                     selectCity(position);
                     selectCityName(position);
                     st_state=reversed[position];
                 }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        cities_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0){
                    st_city=city_nameArray[position];
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


    }


    public boolean validatepanNum() {
        this.panNo = this.panNumber.getText().toString().trim();
        if (this.panNo.isEmpty()) {
            this.panNumber.setError("Enter PAN number");
            return false;
        } else if (this.panNo.matches("[A-Z]{3}[ABCFGHLJPTF]{1}[A-Z]{1}[0-9]{4}[A-Z]{1}")) {
            return true;
        } else {
            this.panNumber.setError("Pan Number is Not Valid");
            return false;
        }

    }


    public boolean validateAdharNum() {
        this.AdharNo = this.adharNumber.getText().toString().trim();
        if (this.AdharNo.isEmpty()) {
            this.adharNumber.setError("Enter Username");
            return false;
        } else if (this.AdharNo.matches("^(\\d{12}|\\d{16})$")) {
            return true;
        } else {
            this.adharNumber.setError("ADHAR Number is Not Valid");
            return false;
        }

    }



     int genrateRandom(){
         int min = 125000;
         int max = 200000;
         Random r = new Random();
         return r.nextInt(max - min + 1) + min;
    }




    public boolean validateusername() {
        this.uname = this.userName.getText().toString().trim();
        if (this.uname.isEmpty()) {
            this.userName.setError("Enter Username");
            return false;
        }
//        } else if (this.uname.matches("[a-zA-Z0-9]*")) {
//            return true;
//        }
//         else {
//            this.userName.setError("No special characters allowed");
//            return false;
//        }
        return  true;
    }


    public boolean isvalideemail(String str) {
        return Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }

    public boolean validateemail() {
        this.eMail = this.emailAddress.getText().toString().trim();
        if (!this.eMail.isEmpty()) {
            if (isvalideemail(this.eMail)) {
                return true;
            }
        }
        this.emailAddress.setError("Enter Valid Email Address");
        return false;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.registerBtn :
                 if(validateAdharNum() && validatepanNum() && validateusername() && validateemail() ){
                  bar.setVisibility(View.VISIBLE);
                   if (st_city!=null && st_state!=null) {
                       addIntoDb();
                   }else{
                       Toast.makeText(this, "Please Select State and City", Toast.LENGTH_SHORT).show();
                   }
                 }
                break;
        }

    }


    void addIntoDb(){
        USER user = new USER();
        user.setName(this.uname);
        user.setAdharNumber(AdharNo);
        user.setPanNumber(panNo);
        user.setEmail(eMail);
        user.setState(st_state);
        user.setCity(st_city);

        user.setAmount(""+genrateRandom());
        user.setPhone(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
        userRef.child(FirebaseAuth.getInstance().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                     if(task.isComplete()){
                         bar.setVisibility(View.GONE);
                         Intent mainIntent = new Intent(getApplicationContext(), AuthLoading.class);
                         startActivity(mainIntent);
                         overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                     }else{
                         bar.setVisibility(View.GONE);
                         Toast.makeText(Registration.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                     }
            }
        });




    }



    void  selectCity(int position){
        ArrayAdapter<String> citiArrayAdapter=null;
        switch (position){
            case 1 :
                 citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, AP);


                break;
            case 2 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, Ar);

                break;

            case 3 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, As);
                break;
            case 4 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, br);
                break;
            case 5 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, CG);
                break;
            case 6 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, Dn);
                break;

            case 7 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, Dd);

                break;
            case 8 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, dl);
                break;
            case 9 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, ga);
                break;
            case 10 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, GJ);
                break;
            case 11 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, hr);
                break;
            case 12 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, Hp);
                break;
            case 13 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, JmK);
                break;
            case 14 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, jh);
                break;
            case 15 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, Ka);
                break;
            case 16 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, Kl);
                break;
            case 17 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, Mp);
                break;
            case 18 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, Mh);
                break;
            case 19:
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, MN);
                break;
            case 20 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, ML);
                break;
            case 21 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, mz);
                break;
            case 22 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, Nl);
                break;
            case 23 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, or);
                break;
            case 24 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, pr);
                break;
            case 25 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, Pun);
                break;
            case 26 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, rj);
                break;
            case 27 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, sk);
                break;
            case 28 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, tn);
                break;
            case 29 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, tr);
                break;
            case 30 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, up);
                break;
            case 31 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, uk);
                break;
            case 32 :
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, wb);
                break;

            default:
                citiArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, state);

                break;


        }
        citiArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        cities_sp.setAdapter(citiArrayAdapter);



    }



    void  selectCityName(int position){
        switch (position){
            case 1 :
                city_nameArray= AP;


                break;
            case 2 :
                city_nameArray= Ar;

                break;

            case 3 :
                city_nameArray= As;
                break;
            case 4 :
                city_nameArray= br;
                break;
            case 5 :
                city_nameArray= CG;
                break;
            case 6 :
                city_nameArray= Dn;
                break;

            case 7 :
                city_nameArray= Dd;

                break;
            case 8 :
                city_nameArray= dl;
                break;
            case 9 :
                city_nameArray= ga;
                break;
            case 10 :
                city_nameArray= GJ;
                break;
            case 11 :
                city_nameArray= hr;
                break;
            case 12 :
                city_nameArray= Hp;
                break;
            case 13 :
                city_nameArray= JmK;
                break;
            case 14 :
                city_nameArray= jh;
                break;
            case 15 :
                city_nameArray= Ka;
                break;
            case 16 :
                city_nameArray= Kl;
                break;
            case 17 :
                city_nameArray= Mp;
                break;
            case 18 :
                city_nameArray= Mh;
                break;
            case 19:
                city_nameArray= MN;
                break;
            case 20 :
                city_nameArray= ML;
                break;
            case 21 :
                city_nameArray= mz;
                break;
            case 22 :
                city_nameArray= Nl;
                break;
            case 23 :
                city_nameArray= or;
                break;
            case 24 :
                city_nameArray= pr;
                break;
            case 25 :
                city_nameArray= Pun;
                break;
            case 26 :
                city_nameArray= rj;
                break;
            case 27 :
                city_nameArray= sk;
                break;
            case 28 :
                city_nameArray= tn;
                break;
            case 29 :
                city_nameArray= tr;
                break;
            case 30 :
                city_nameArray= up;
                break;
            case 31 :
                city_nameArray= uk;
                break;
            case 32 :
                city_nameArray= wb;
                break;

            default:
                city_nameArray= Ar;

                break;


        }



    }


}
