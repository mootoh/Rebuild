Rebuild.fm for Android [![Build Status](https://travis-ci.org/rejasupotaro/Rebuild.png?branch=master)](https://travis-ci.org/rejasupotaro/Rebuild) [![wercker status](https://app.wercker.com/status/bac4cd5c8d65b004a6b81f8f01ef5524 "wercker status")](https://app.wercker.com/project/bykey/bac4cd5c8d65b004a6b81f8f01ef5524)
======

Rebuild.fm for Android is the best podcast player to listen to Rebuild.fm. (Unofficial)

About Rebuild.fm
------
A [Podcast](http://rebuild.fm/) by [Tatsuhiko Miyagawa](https://twitter.com/miyagawa). Talking about Tech, Software Development and Gadgets.

Screenshot
------

<img src="https://raw2.github.com/rejasupotaro/Rebuild/master/screenshot.png">

Installation
------

### GET IT ON Google Play

[![](https://dl.dropboxusercontent.com/u/54255753/blog/201402/en_generic_rgb_wo_60.png)](https://play.google.com/store/apps/details?id=rejasupotaro.rebuild)

### Build on local

```
$ git clone https://github.com/rejasupotaro/Rebuild.git
$ cd Rebuild
$ git submodule update --init
$ ./gradlew installStagingDebug
```

Run Test
------

### Run unit tests

```
$ ./gradlew connectedInstrumentTestStagingDebug
```

### Run UI tests and take screenshots

```
$ ./gradlew calabashStagingDebug
```

Contributing
------

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request

Contributors
------

<img src="https://raw.github.com/rejasupotaro/Rebuild/master/contributors.png" width="360">
