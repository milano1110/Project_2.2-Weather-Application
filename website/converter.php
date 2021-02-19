<?php
function convertCsvToXmlFile($input_file, $output_file) {
// Open csv file for reading
    $inputFile  = fopen($input_file, 'rt');

// Get the headers of the file
    $headers = fgetcsv($inputFile);

// Create a new dom document with pretty formatting
    $doc  = new DomDocument("1.0","UTF-8");
    $doc->formatOutput   = true;

// Add a root node to the document
    $root = $doc->createElement('WEATHERDATA');
    $root = $doc->appendChild($root);

// Loop through each row creating a <measurement> node with the correct data
    while (($row = fgetcsv($inputFile)) !== FALSE)
    {
        $container = $doc->createElement('MEASUREMENT');

        foreach($headers as $i => $header)
        {
            $child = $doc->createElement($header ?? "");
            $child = $container->appendChild($child ?? "");
            $value = $doc->createTextNode($row[$i] ?? "");
            $value = $child->appendChild($value ?? "");
        }
        $root->appendChild($container);
    }

    $strxml = $doc->saveXML();

    $handle = fopen($output_file, "w");
    fwrite($handle, $strxml);
    fclose($handle);
}