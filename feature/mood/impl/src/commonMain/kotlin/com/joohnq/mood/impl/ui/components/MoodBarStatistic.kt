package com.joohnq.mood.impl.ui.components

//@Composable
//fun MoodBarStatistic(
//    records: List<MoodRecordResource>,
//    height: Dp = 250.dp,
//) {
//    val current = records.last()
//    val proportion = height / 100
//    val pagerState = rememberPagerState(pageCount = {
//        records.size
//    })
//
//    val selectedIndex = records.indexOf(current)
//
//    LaunchedEffect(selectedIndex) {
//        if (selectedIndex >= 0) {
//            pagerState.animateScrollToPage(selectedIndex)
//        }
//    }
//    val textHeight = CalculateTextHeight(font = TextStyles.TextSmSemiBold())
//    val resource = current.mood.toResource()
//    BoxWithConstraints(
//        modifier = Modifier.fillMaxWidth().height(height + textHeight)
//    ) {
//        val usableWidth = maxWidth - (15.dp * 8)
//        val boxWidth = usableWidth / 7
//
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.BottomStart
//        ) {
//            Box(modifier = Modifier.fillMaxSize()) {
//                Column {
//                    repeat(5) {
//                        Box(
//                            modifier = Modifier.fillMaxWidth().height(height / 5).dashedLine(
//                                color = Colors.Brown20,
//                            )
//                        )
//                    }
//                }
//            }
//            HorizontalPager(
//                contentPadding = PaddingValues(horizontal = 15.dp),
//                state = pagerState,
//                pageSize = PageSize.Fixed(boxWidth),
//                pageSpacing = 15.dp,
//                verticalAlignment = Alignment.Bottom,
//                beyondViewportPageCount = 3,
//                userScrollEnabled = true,
//                snapPosition = SnapPosition.Center,
//            ) { page ->
//                val record = records[page]
//                val boxHeight = record.mood.healthLevel.times(proportion)
//                val background = when {
//                    current == record -> resource.palette.barColor
//                    else -> Colors.Brown20
//                }
//                val barFaceColor = when {
//                    current == record -> resource.palette.barFaceColor
//                    else -> Colors.Brown40
//                }
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Bottom,
//                    modifier = Modifier.width(boxWidth).fillMaxHeight()
//                ) {
//                    Card(
//                        modifier = Modifier
//                            .width(boxWidth)
//                            .height(boxHeight),
//                        colors = CardColors(
//                            containerColor = barFaceColor,
//                            disabledContainerColor = barFaceColor,
//                            contentColor = barFaceColor,
//                            disabledContentColor = barFaceColor,
//                        ),
//                        shape = RoundedCornerShape(topEnd = 100.dp, topStart = 100.dp),
//                    ) {
//                        Column(
//                            modifier = Modifier.fillMaxSize().padding(5.dp),
//                            verticalArrangement = Arrangement.Top,
//                            horizontalAlignment = Alignment.CenterHorizontally
//                        ) {
//                            MoodFace(
//                                modifier = Modifier.size(boxWidth - 10.dp),
//                                record = record.mood.toResource(),
//                                backgroundColor = Colors.White,
//                                color = barFaceColor
//                            )
//                        }
//                    }
//                    Text(
//                        text = record.createdAt.date.toShortDateString(),
//                        style = TextStyles.TextSmSemiBold(),
//                        color = Colors.Brown100Alpha64,
//                        modifier = Modifier
//                    )
//                }
//            }
//        }
//    }
//}

