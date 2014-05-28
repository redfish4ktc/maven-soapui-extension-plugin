==============================
Content of the setup projects
==============================

Note: remember that currently, we cannot exclude dependencies of a plugin, but we can override version of plugin dependencies
These setup files have been created to workaround #125

=================
javafx_workaround
=================
Install a empty jar for javafx to be able to exclude this dependency from SmartBear plugins


=================
smartbear-soapui-plugins-installation-without-javafx
=================
Install SmartBear plugins and their dependencies in the local repository but the javafx dependency
It was previously done by configuring the extraArtifacts parameter (of the invoker plugin) when there was no need to exclude dependency

