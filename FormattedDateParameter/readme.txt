
 Formatted Date Parameter TeamCity server-side plugin

 This Plugin provides the %build.formatted.timestamp% confguration parameter,
 which during build will contain the build start timestamp. The timestamp format is ISO-1806 by default ("yyyy-MM-dd'T'HH:mmZ").
 The timestamp format can be configured using the %build.timestamp.format% configuration parameter, consult http://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html for syntax.
