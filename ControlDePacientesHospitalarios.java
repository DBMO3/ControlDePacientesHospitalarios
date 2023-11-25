package controldepacienteshospitalarios;

// Librerias para manipular archivos CSV
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ControlDePacientesHospitalarios {

    // public static se utiliza aquí para garantizar que estas variables estén disponibles 
    // y compartidas a nivel de clase, en lugar de a nivel de instancia.
    public static Scanner in = new Scanner(System.in);

    // Variables
    private static String id;
    private static String first_name;
    private static String last_name;
    private static String address;
    private static String phone;
    private static String nationality;
    private static String gender;
    private static String nss;
    private static String loand;
    private static String incapability;
    private static String death;
    private static String discharge;

    public static void main(String[] args) {
        displayLogin();
    }

    // Muestra el login con opciones
    public static void displayLogin() {
        int selectOption = 0;
        String userFile = "users.csv"; // Archivo de usuarios
        String username, password = "";
        try {
            while (selectOption != 3) {
                System.out.println("------------------------------------");
                System.out.println(" Control de pacientes hospitalarios ");
                System.out.println("------------------------------------");
                System.out.println("          1. Registro               ");
                System.out.println("          2. Iniciar sesion         ");
                System.out.println("          3. Salir                  ");
                System.out.println("------------------------------------");

                selectOption = in.nextInt();

                switch (selectOption) {
                    case 1:
                        System.out.println("Ingresa el nombre de usuario: ");
                        username = in.next();

                        System.out.println("Ingresa una clave: ");
                        password = in.next();
                        try {
                            // Escribe el ususario y clave en el archivo users.csv
                            FileWriter fw = new FileWriter(userFile, true);
                            CSVWriter write = new CSVWriter(fw);
                            
                            String[] userData = {username, password};
                            
                            write.writeNext(userData);
                            write.close(); // Cerrar flujo de escritura para evitar fugas de recursos
                            System.out.println("Usuario registrado exitosamente");
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("Error al realizar el registro");
                        }
                        break;
                    case 2:
                        System.out.println("Ingresa el nombre de usuario: ");
                        username = in.next();

                        System.out.println("Ingresa una clave: ");
                        password = in.next();

                        try {
                            // Se lee el archivo y si el usuario y clave existe y coincide, accede al programa
                            FileReader fr = new FileReader(userFile);
                            CSVReader read = new CSVReader(fr);
                            ArrayList<String[]> allUsers = (ArrayList<String[]>) read.readAll();

                            boolean userFound = false;
                            for (String[] userData : allUsers) {
                                if (userData[0].equals(username) && userData[1].equals(password)) {
                                    userFound = true;
                                    break;
                                }
                            }

                            if (userFound) {
                                System.out.println("Inicio de sesion exitoso para el usuario: " + username);
                                menu();
                            } else {
                                // De lo contrario, no accedera al programa
                                System.out.println("Usuario no encontrado o clave incorrecta");
                            }

                            fr.close();
                            read.close();
                            // Cerrar flujos de lectura para evitar fuga de recursos

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    case 3:
                        System.out.println("-----------------------------------");
                        System.out.println("   Gracias por usar el programa   ");
                        System.out.println("-----------------------------------");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Opcion invalida. Intente de nuevo");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void menu() {
        String dataFile = "data.csv"; // Archivo de |000 datos
        int indexField;
        int optionMenu = 0;

        ArrayList<String[]> allData = loadData(dataFile); // Estructura de datos ArrayList (Objeto: AllData)

        while (optionMenu != 5) {
            displayMenu();
            optionMenu = in.nextInt();
            in.nextLine();

            switch (optionMenu) {
                case 1:
                    System.out.println("ID: ");
                    id = in.nextLine();
                    System.out.println("Nombre: ");
                    first_name = in.nextLine();
                    System.out.println("Apellido: ");
                    last_name = in.nextLine();
                    System.out.println("Direccion: ");
                    address = in.nextLine();
                    System.out.println("Telefono: ");
                    phone = in.nextLine();
                    System.out.println("Nacionalidad: ");
                    nationality = in.nextLine();
                    System.out.println("Genero: ");
                    gender = in.nextLine();
                    System.out.println("SSN: ");
                    nss = in.nextLine();
                    System.out.println("El paciente cuenta con prestamos?: ");
                    loand = in.nextLine();
                    System.out.println("El paciente cuenta con incapacidad?: ");
                    incapability = in.nextLine();
                    System.out.println("El paciente murio?: ");
                    death = in.nextLine();
                    System.out.println("Fecha de alta del paciente: ");
                    discharge = in.nextLine();
                    writeData(allData, dataFile); // Inserta los datos en el archivo
                    allData = loadData(dataFile); // Se realiza la carga de los datos

                    System.out.println("Datos guardados con exito");
                    break;

                case 2:
                    displayAllData(allData); // Mostrar todos los datos
                    break;

                case 3:
                    displayOptions();
                    boolean selectionIndexField = false;
                    while (!selectionIndexField) {
                        indexField = in.nextInt();
                        switch (indexField) {
                            case 2:
                                randomSelectionSort(allData, indexField);
                                selectionIndexField = true;
                                break;
                            case 7:
                                randomSelectionSort(allData, indexField);
                                selectionIndexField = true;
                                break;
                            case 0:
                                selectionIndexField = true;
                                break;
                            default:
                                System.out.println("Opcion no valida. Intente de nueco");
                                break;
                        }
                    }
                    break;

                case 4:
                    displayOptions();
                    boolean searchIndexField = false;
                    while (!searchIndexField) {
                        indexField = in.nextInt();
                        in.nextLine();
                        switch (indexField) {
                            case 2:
                                System.out.println("Ingresa el Apellido: ");
                                String last_name = in.nextLine();
                                randomSelectionSearch(allData, last_name, indexField);
                                break;
                            case 7:
                                System.out.println("Ingrese el NSS: ");
                                String nss = in.nextLine();
                                randomSelectionSearch(allData, nss, indexField);
                                break;
                            case 0:
                                searchIndexField = true;
                                break;
                            default:
                                System.out.println("Opcion no valida");
                                break;
                        }
                    }
                    break;

                case 5:
                    break;

                default:
                    System.out.println("-------------------------------------------");
                    System.out.println("Opcion invalida. Porfavor intene de nuevo");
                    System.out.println("-------------------------------------------");
                    break;
            }
        }
    }

    // Menú y opciones
    public static void displayMenu() {
        System.out.println("****************************************");
        System.out.println("** Control de pacientes hospitalarios **");
        System.out.println("****************************************");
        System.out.println("**           1. Insertar              **");
        System.out.println("**           2. Mostrar               **");
        System.out.println("**           3. Ordenar               **");
        System.out.println("**           4. Buscar                **");
        System.out.println("**           5. Salir                 **");
        System.out.println("****************************************");
        System.out.println("**   Copyright (c) DBMO3 & AxmarYT    **");
        System.out.println("****************************************");
        System.out.println("****************************************");
        System.out.println("**       Selecciona una opcion        **");
        System.out.println("****************************************");
    }

    // Carga todos los datos desde el archivo CSV
    public static ArrayList<String[]> loadData(String dataFile) {
        ArrayList<String[]> allData = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(dataFile);
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();

            allData = (ArrayList<String[]>) csvReader.readAll();
            fileReader.close();
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allData;
    }

    // Escribir todos los datos desde el archivo CSV
    public static void writeData(ArrayList<String[]> allData, String dataFile) {
        try {
            FileWriter fileWriter = new FileWriter(dataFile, true);
            CSVWriter csvWriter = new CSVWriter(fileWriter);

            String[] Data = {id, first_name, last_name, address, phone, nationality, gender, nss, loand, incapability, death, discharge};

            // Escribir los datos
            csvWriter.writeNext(Data);

            // Cerrar los flujos
            csvWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Opciones para seleccionar el campo clave a ordenar
    public static void displayOptions() {
        System.out.println("-----------------------------------");
        System.out.println("Selecciona el campo:");
        System.out.println("2. Apellido ");
        System.out.println("7. NSS");
        System.out.println("0. Salir");
        System.out.println("-----------------------------------");
    }

    // Mostrar fila de datos
    public static void displayRow(String[] row) {
        System.out.println("---------------------------------");
        System.out.println("ID: " + row[0]);
        System.out.println("Nombre: " + row[1]);
        System.out.println("Apellido: " + row[2]);
        System.out.println("Direccion: " + row[3]);
        System.out.println("Telefono: " + row[4]);
        System.out.println("Nacionalidad: " + row[5]);
        System.out.println("Genero: " + row[6]);
        System.out.println("NSS: " + row[7]);
        System.out.println("Prestamos: " + row[8]);
        System.out.println("Incapacidad: " + row[9]);
        System.out.println("Defusion: " + row[10]);
        System.out.println("Alta del paciente: " + row[11]);
        System.out.println("---------------------------------");
    }

    // Muestra todos los datos
    public static void displayAllData(ArrayList<String[]> allData) {
        for (String[] row : allData) {
            displayRow(row);
        }
    }

    // Función para seleccionar aleatoriamente el metodo de ordenamiento
    public static void randomSelectionSort(ArrayList<String[]> allData, int indexField) {
        Random random = new Random();
        int randomSelection = random.nextInt(11);
        long startTime = System.nanoTime();

        switch (randomSelection) {
            case 0:
                bubbleSortString(allData, indexField);
                System.out.println("Ordenado por Bubble Sort");
                break;
            case 1:
                cocktailBubbleSortString(allData, indexField);
                System.out.println("Ordenado por Cocktail Bubble Sort");
                break;
            case 2:
                selectionSortString(allData, indexField);
                System.out.println("Ordenado por Selection Sort");
                break;
            case 3:
                insertionSortString(allData, indexField);
                System.out.println("Ordenado por Insertion Sort");
                break;
            case 4:
                shellSortString(allData, indexField);
                System.out.println("Ordenado por Shell Sort");
                break;
            case 5:
                quickSortString(allData, indexField, 0, allData.size() - 1);
                System.out.println("Ordenado por Quick Sort");
                break;
            case 6:
                mergeSortString(allData, indexField, 0, allData.size() - 1);
                System.out.println("Ordenado por Merge Sort");
                break;
            case 7:
                radixSortString(allData, indexField);
                System.out.println("Ordenado por Radix Sort");
                break;
            case 8:
                bucketSortString(allData, indexField);
                System.out.println("Ordenado por Bucket Sort");
                break;
            case 9:
                countingSortString(allData, indexField);
                System.out.println("Ordenado por Counting Sort");
                break;
            case 10:
                heapSortString(allData, indexField);
                System.out.println("Ordenado por Heap Sort");
                break;
        }

        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("Tiempo: " + time + " nanosegundos");
    }

    // Funcion para seleccionar aleatoriamente el metodo de busqueda
    public static void randomSelectionSearch(ArrayList<String[]> allData, String key, int indexField) {
        Random random = new Random();
        int randomSelection = random.nextInt(2);
        long startTime = System.nanoTime();

        switch (randomSelection) {
            case 0:
                boolean isSorted = isDataSorted(allData, indexField);

                if (!isSorted) {
                    System.out.println("Los datos no estan ordenados. Se recomienda ordenarlos antes de realizar la busqueda binaria.");
                    displayOptions();
                    return;
                }

                int binaryResult = binarySearch(allData, key, indexField);
                if (binaryResult != -1) {
                    displayRow(allData.get(binaryResult));
                } else {
                    System.out.println("El paciente no fue encontrado.");
                }
                System.out.println("Buscado usando el metodo de busqueda binaria");
                break;
            case 1:
                int sequentialResult = sequentialSearch(allData, key, indexField);
                if (sequentialResult != -1) {
                    displayRow(allData.get(sequentialResult));
                } else {
                    System.out.println("El paciente no fue encontrado.");
                }
                System.out.println("Buscado usando el metodo de busqueda secuencial");
                break;
        }

        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("Tiempo: " + time + " nanosegundos");
        displayOptions();
    }

    // Bubble sort
    public static void bubbleSortString(ArrayList<String[]> allData, int indexField) {
        int n = allData.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compareFields(allData.get(j)[indexField], allData.get(j + 1)[indexField], indexField) > 0) {
                    String[] temp = allData.get(j);
                    allData.set(j, allData.get(j + 1));
                    allData.set(j + 1, temp);
                }
            }
        }
    }

    // Cocktail sort (Burbuja bidireccional)
    public static void cocktailBubbleSortString(ArrayList<String[]> allData, int indexField) {
        int n = allData.size();
        boolean swapped = true;
        int start = 0;
        int end = n - 1;

        while (swapped) {
            // Similar a bubble sort de izquierda a derecha
            swapped = false;
            for (int i = start; i < end; ++i) {
                if (compareFields(allData.get(i)[indexField], allData.get(i + 1)[indexField], indexField) > 0) {
                    String[] temp = allData.get(i);
                    allData.set(i, allData.get(i + 1));
                    allData.set(i + 1, temp);
                    swapped = true;
                }
            }

            // Si no hubo intercambios, la lista ya está ordenada
            if (!swapped) {
                break;
            }

            // Reducir la ventana hacia la derecha
            end--;

            // Similar a bubble sort de derecha a izquierda
            swapped = false;
            for (int i = end - 1; i >= start; --i) {
                if (compareFields(allData.get(i)[indexField], allData.get(i + 1)[indexField], indexField) > 0) {
                    String[] temp = allData.get(i);
                    allData.set(i, allData.get(i + 1));
                    allData.set(i + 1, temp);
                    swapped = true;
                }
            }

            // Ampliar la ventana hacia la izquierda
            start++;
        }
    }

    // Selection sort
    public static void selectionSortString(ArrayList<String[]> allData, int indexField) {
        int n = allData.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (compareFields(allData.get(j)[indexField], allData.get(minIndex)[indexField], indexField) < 0) {
                    minIndex = j;
                }
            }
            String[] temp = allData.get(i);
            allData.set(i, allData.get(minIndex));
            allData.set(minIndex, temp);
        }
    }

    // Método de ordenamiento de inserción
    public static void insertionSortString(ArrayList<String[]> allData, int indexField) {
        int n = allData.size();
        for (int i = 1; i < n; ++i) {
            String[] key = allData.get(i);
            int j = i - 1;

            while (j >= 0 && compareFields(allData.get(j)[indexField], key[indexField], indexField) > 0) {
                allData.set(j + 1, allData.get(j));
                j = j - 1;
            }
            allData.set(j + 1, key);
        }
    }

    // Método de ordenamiento de shell
    public static void shellSortString(ArrayList<String[]> allData, int indexField) {
        int n = allData.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                String[] temp = allData.get(i);
                int j;
                for (j = i; j >= gap && compareFields(allData.get(j - gap)[indexField], temp[indexField], indexField) > 0; j -= gap) {
                    allData.set(j, allData.get(j - gap));
                }
                allData.set(j, temp);
            }
        }
    }

    // Método de ordenamiento de Quick Sort
    public static void quickSortString(ArrayList<String[]> allData, int indexField, int low, int high) {
        if (low < high) {
            int pi = partition(allData, indexField, low, high);

            quickSortString(allData, indexField, low, pi - 1);
            quickSortString(allData, indexField, pi + 1, high);
        }
    }

    // Particiones
    private static int partition(ArrayList<String[]> allData, int indexField, int low, int high) {
        String pivot = allData.get(high)[indexField];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (compareFields(allData.get(j)[indexField], pivot, indexField) < 0) {
                i++;
                String[] temp = allData.get(i);
                allData.set(i, allData.get(j));
                allData.set(j, temp);
            }
        }
        String[] temp = allData.get(i + 1);
        allData.set(i + 1, allData.get(high));
        allData.set(high, temp);

        return i + 1;
    }

    // Método de ordenamiento de Merge Sort
    public static void mergeSortString(ArrayList<String[]> allData, int indexField, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            mergeSortString(allData, indexField, left, middle);
            mergeSortString(allData, indexField, middle + 1, right);

            merge(allData, indexField, left, middle, right);
        }
    }

    private static void merge(ArrayList<String[]> allData, int indexField, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        String[][] L = new String[n1][];
        String[][] R = new String[n2][];

        for (int i = 0; i < n1; ++i) {
            L[i] = allData.get(left + i);
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = allData.get(middle + 1 + j);
        }

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (compareFields(L[i][indexField], R[j][indexField], indexField) <= 0) {
                allData.set(k, L[i]);
                i++;
            } else {
                allData.set(k, R[j]);
                j++;
            }
            k++;
        }

        while (i < n1) {
            allData.set(k, L[i]);
            i++;
            k++;
        }

        while (j < n2) {
            allData.set(k, R[j]);
            j++;
            k++;
        }
    }

    // Método de ordenamiento Radix Sort
    public static void radixSortString(ArrayList<String[]> allData, int indexField) {
        int n = allData.size();
        int maxLength = getMaxStringLength(allData, indexField);

        for (int i = maxLength - 1; i >= 0; i--) {
            countingSortByDigit(allData, indexField, i);
        }
    }

// Obtener la longitud máxima de la cadena en el índice especificado
    private static int getMaxStringLength(ArrayList<String[]> allData, int indexField) {
        int maxLength = 0;
        for (String[] row : allData) {
            int length = row[indexField].length();
            if (length > maxLength) {
                maxLength = length;
            }
        }
        return maxLength;
    }

// Método de ordenamiento Bucket Sort
    public static void bucketSortString(ArrayList<String[]> allData, int indexField) {
        int n = allData.size();
        final int BUCKET_SIZE = 10; // Tamaño del cubo

        ArrayList<String[]> buckets[] = new ArrayList[BUCKET_SIZE];

        // Inicializar los cubos
        for (int i = 0; i < BUCKET_SIZE; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Colocar los elementos en los cubos
        for (int i = 0; i < n; i++) {
            int bucketIndex = getBucketIndex(allData.get(i)[indexField], BUCKET_SIZE);
            buckets[bucketIndex].add(allData.get(i));
        }

        // Ordenar individualmente los cubos y colocar los elementos ordenados de nuevo en la lista principal
        int currentIndex = 0;
        for (int i = 0; i < BUCKET_SIZE; i++) {
            insertionSortString(buckets[i], indexField); // Puedes usar cualquier algoritmo de ordenamiento aquí

            for (int j = 0; j < buckets[i].size(); j++) {
                allData.set(currentIndex++, buckets[i].get(j));
            }
        }
    }

// Obtener el índice del cubo para un valor dado
    private static int getBucketIndex(String value, int bucketSize) {
        int sum = 0;
        for (int i = 0; i < value.length(); i++) {
            sum += value.charAt(i);
        }
        return sum % bucketSize;
    }

// Ordenar utilizando Counting Sort basado en el dígito actual
    private static void countingSortByDigit(ArrayList<String[]> allData, int indexField, int exp) {
        final int RANGE = 256; // Extendido ASCII
        int n = allData.size();

        String[][] output = new String[n][];
        int[] count = new int[RANGE];

        // Inicializar el arreglo de conteo
        for (int i = 0; i < RANGE; ++i) {
            count[i] = 0;
        }

        // Contar la ocurrencia de cada carácter
        for (int i = 0; i < n; ++i) {
            int charIndex = exp < allData.get(i)[indexField].length() ? allData.get(i)[indexField].charAt(allData.get(i)[indexField].length() - exp - 1) : 0;
            count[charIndex]++;
        }

        // Calcular las posiciones reales de los caracteres en el arreglo de salida
        for (int i = 1; i < RANGE; ++i) {
            count[i] += count[i - 1];
        }

        // Construir el arreglo de salida
        for (int i = n - 1; i >= 0; i--) {
            int charIndex = exp < allData.get(i)[indexField].length() ? allData.get(i)[indexField].charAt(allData.get(i)[indexField].length() - exp - 1) : 0;
            output[count[charIndex] - 1] = allData.get(i);
            count[charIndex]--;
        }

        // Copiar el arreglo de salida al arreglo original
        for (int i = 0; i < n; ++i) {
            allData.set(i, output[i]);
        }
    }

// Método de ordenamiento Counting Sort
    public static void countingSortString(ArrayList<String[]> allData, int indexField) {
        int n = allData.size();
        final int RANGE = 256; // Extendido ASCII

        String[][] output = new String[n][];
        int[] count = new int[RANGE];

        // Inicializar el arreglo de conteo
        for (int i = 0; i < RANGE; ++i) {
            count[i] = 0;
        }

        // Contar la ocurrencia de cada carácter
        for (int i = 0; i < n; ++i) {
            int charIndex = allData.get(i)[indexField].length() > 0 ? allData.get(i)[indexField].charAt(0) : 0;
            count[charIndex]++;
        }

        // Calcular las posiciones reales de los caracteres en el arreglo de salida
        for (int i = 1; i < RANGE; ++i) {
            count[i] += count[i - 1];
        }

        // Construir el arreglo de salida
        for (int i = n - 1; i >= 0; i--) {
            int charIndex = allData.get(i)[indexField].length() > 0 ? allData.get(i)[indexField].charAt(0) : 0;
            output[count[charIndex] - 1] = allData.get(i);
            count[charIndex]--;
        }

        // Copiar el arreglo de salida al arreglo original
        for (int i = 0; i < n; ++i) {
            allData.set(i, output[i]);
        }
    }

// Método de ordenamiento Heap Sort
    public static void heapSortString(ArrayList<String[]> allData, int indexField) {
        int n = allData.size();

        // Construir el montículo (heap)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(allData, indexField, n, i);
        }

        // Extraer elementos del montículo uno por uno
        for (int i = n - 1; i > 0; i--) {
            // Mover la raíz actual al final
            String[] temp = allData.get(0);
            allData.set(0, allData.get(i));
            allData.set(i, temp);

            // Llamar al heapify en el montículo reducido
            heapify(allData, indexField, i, 0);
        }
    }

// Para convertir un subárbol en montículo
    private static void heapify(ArrayList<String[]> allData, int indexField, int n, int i) {
        int largest = i; // Inicializar el nodo más grande como la raíz
        int leftChild = 2 * i + 1; // Izquierda = 2*i + 1
        int rightChild = 2 * i + 2; // Derecha = 2*i + 2

        // Si el hijo izquierdo es más grande que la raíz
        if (leftChild < n && compareFields(allData.get(leftChild)[indexField], allData.get(largest)[indexField], indexField) > 0) {
            largest = leftChild;
        }

        // Si el hijo derecho es más grande que el más grande hasta ahora
        if (rightChild < n && compareFields(allData.get(rightChild)[indexField], allData.get(largest)[indexField], indexField) > 0) {
            largest = rightChild;
        }

        // Si el más grande no es la raíz
        if (largest != i) {
            String[] swap = allData.get(i);
            allData.set(i, allData.get(largest));
            allData.set(largest, swap);

            // Recursivamente continuar con el montículo afectado
            heapify(allData, indexField, n, largest);
        }
    }

    // Metodos de Busqueda
    // Método de búsqueda binaria
    public static int binarySearch(ArrayList<String[]> allData, String key, int indexField) {
        int left = 0;
        int right = allData.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            String midValue = allData.get(mid)[indexField];

            int compareResult = compareFields(midValue, key, indexField);

            if (compareResult == 0) {
                return mid; // Se encontró el elemento
            } else if (compareResult < 0) {
                left = mid + 1; // Buscar en la mitad derecha
            } else {
                right = mid - 1; // Buscar en la mitad izquierda
            }
        }

        return -1; // No se encontró el elemento
    }

// Método de búsqueda secuencial
    public static int sequentialSearch(ArrayList<String[]> allData, String key, int indexField) {
        for (int i = 0; i < allData.size(); i++) {
            String value = allData.get(i)[indexField];
            if (compareFields(value, key, indexField) == 0) {
                return i; // Se encontró el elemento
            }
        }

        return -1; // No se encontró el elemento
    }

    // Comparar campos basados en el indice
    public static int compareFields(String value1, String value2, int indexField) {
        switch (indexField) {
            case 2:
                return compareLastNames(value1, value2);
            case 7:
                return compareNSS(value1, value2);
        }
        return 0;
    }

    // Comparar apellidos
    public static int compareLastNames(String lastName1, String lastName2) {
        return lastName1.compareToIgnoreCase(lastName2);
    }

    // Comparar numeros de telefono
    public static int compareNSS(String NSS1, String NSS2) {
        return NSS1.compareTo(NSS2);
    }

    // Función para verificar si los datos están ordenados
    public static boolean isDataSorted(ArrayList<String[]> allData, int indexField) {
        for (int i = 1; i < allData.size(); i++) {
            String current = allData.get(i)[indexField];
            String previous = allData.get(i - 1)[indexField];

            if (compareFields(current, previous, indexField) < 0) {
                return false; // Los datos no están ordenados
            }
        }

        return true; // Los datos están ordenados
    }
}
